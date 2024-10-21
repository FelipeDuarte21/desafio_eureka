package com.cooperfilme.api.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.cooperfilme.api.dto.RoteiroDTO;
import com.cooperfilme.api.dto.RoteiroDadosDTO;
import com.cooperfilme.api.dto.StatusDadosDTO;
import com.cooperfilme.api.dto.VotoDadosDTO;
import com.cooperfilme.api.entity.Cliente;
import com.cooperfilme.api.entity.Roteiro;
import com.cooperfilme.api.entity.Usuario;
import com.cooperfilme.api.entity.Voto;
import com.cooperfilme.api.entity.enums.TipoStatusRoteiro;
import com.cooperfilme.api.repository.RoteiroRepository;
import com.cooperfilme.api.security.UsuarioDetalhe;
import com.cooperfilme.api.service.exception.IllegalParameterException;
import com.cooperfilme.api.service.exception.ObjectNotFoundFromParameterException;
import com.cooperfilme.api.service.exception.UserAuthorizationException;

import jakarta.transaction.Transactional;

@Service
public class RoteiroService {

    private RoteiroRepository roteiroRepository;

    private ClienteService clienteService;
    private RegistroStatusRoteiroService historicoService;
    private UsuarioService usuarioService;
    private VotoService votoService;

    public RoteiroService(RoteiroRepository roteiroRepository, ClienteService clienteService,
        RegistroStatusRoteiroService historicoService, UsuarioService usuarioService, VotoService votoService){
        this.roteiroRepository = roteiroRepository;
        this.clienteService = clienteService;
        this.historicoService = historicoService;
        this.usuarioService = usuarioService;
        this.votoService = votoService;
    }

    @Transactional(rollbackOn = Exception.class)
    public RoteiroDTO cadastrar(RoteiroDadosDTO dados){
        
        Cliente cliente = clienteService.cadastrar(dados.cliente());

        Roteiro roteiro = converteRoteiroDadosDTOParaRoteiro(dados);
        roteiro.setCliente(cliente);
        roteiro.setStatusAtual(TipoStatusRoteiro.AGUARDANDO_ANALISE);

        roteiroRepository.save(roteiro);

        historicoService.registrar(roteiro, TipoStatusRoteiro.AGUARDANDO_ANALISE, "Roteiro Enviado");

        return converteRoteiroParaRoteiroDTO(cliente, roteiro);

    }

    public List<RoteiroDTO> buscarRoteirosCliente(String nome, String email){

        Cliente cliente = clienteService.buscarCliente(nome, email);
    
        List<Roteiro> roteiros = roteiroRepository.findByCliente(cliente);
    
        return roteiros.stream()
            .map(roteiro -> converteRoteiroParaRoteiroDTO(cliente, roteiro))
                .collect(Collectors.toList());
        
    }

    public RoteiroDTO buscarPorId(Long id){

        Optional<Roteiro> optRoteiro = roteiroRepository.findById(id);

        if(optRoteiro.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro: roteiro não encontrado!");

        RoteiroDTO roteiro = converteRoteiroParaRoteiroDTO(optRoteiro.get().getCliente(), optRoteiro.get());

        return roteiro;

    }

    public List<RoteiroDTO> buscarRoteirosUsuario(UsuarioDetalhe usuarioLogado){

        Usuario usuario = usuarioService.buscarPorId(usuarioLogado.getId());

        List<TipoStatusRoteiro> statusPermitido = usuario.getCargo().getStatusRoteiros();

        List<Roteiro> roteiros = new ArrayList<>();

        for(TipoStatusRoteiro status: statusPermitido){
           roteiros.addAll(roteiroRepository.findByStatusAtual(status));
        }

        return roteiros.stream().map(roteiro -> converteRoteiroParaRoteiroDTO(roteiro.getCliente(), roteiro))
            .collect(Collectors.toList());

    }

    public void votarRoteiro(Long id, VotoDadosDTO dadosVoto, UsuarioDetalhe usuarioLogado){

        Usuario usuario = usuarioService.buscarPorId(usuarioLogado.getId());

        if(!usuario.getCargo().isAprovador()) throw new UserAuthorizationException("Erro: Usuário sem permissão!");

        Optional<Roteiro> optRoteiro = roteiroRepository.findById(id);

        if(optRoteiro.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro: roteiro não encontrado!");

        Roteiro roteiro = optRoteiro.get();

        if(!roteiro.getStatusAtual().isAguardandoAprovacao() && !roteiro.getStatusAtual().isEmAprovacao())
            throw new IllegalParameterException("Erro: roteiro não pode ser votado!");

        if(votoService.verificarSeUsuarioJaVotou(roteiro.getVotos(), usuario.getEmail())) return;
        
        Voto voto = votoService.registrarVoto(roteiro, dadosVoto.decisao(), usuario.getEmail());

        roteiro.addVoto(voto);

        definirSituacaoRoteiro(roteiro, voto, dadosVoto.justificativa());

        historicoService.registrar(roteiro, roteiro.getStatusAtual(), dadosVoto.justificativa());

        roteiroRepository.save(roteiro);
        
    }

    private void definirSituacaoRoteiro(Roteiro roteiro, Voto voto, String justificativa){

        if(roteiro.getVotos().size() == 1 && voto.isAprovado()) {
            roteiro.setStatusAtual(TipoStatusRoteiro.EM_APROVAÇÃO);
        }
            
        if(roteiro.getVotos().size() > 1 && voto.isReprovado()) {
            historicoService.registrar(roteiro, roteiro.getStatusAtual(), justificativa);
            roteiro.setStatusAtual(TipoStatusRoteiro.RECUSADO);
        }
    
        if(roteiro.getVotos().size() == 3 && voto.isAprovado()) {
            historicoService.registrar(roteiro, roteiro.getStatusAtual(), justificativa);
            roteiro.setStatusAtual(TipoStatusRoteiro.APROVADO);
        }

    }

    public void mudarStatusRoteiro(Long id, StatusDadosDTO dados, UsuarioDetalhe usuarioLogado) {

        Usuario usuario = usuarioService.buscarPorId(usuarioLogado.getId());

        
        Optional<Roteiro> optRoteiro = roteiroRepository.findById(id);
        
        if(optRoteiro.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro: roteiro não encontrado!");
        
        Roteiro roteiro = optRoteiro.get();
       
        if(!usuario.getCargo().getStatusRoteiros().contains(roteiro.getStatusAtual())) 
            throw new UserAuthorizationException("Erro: Usuário sem permissão!");
        
        if(roteiro.getStatusAtual().isAguardandoAprovacao() || roteiro.getStatusAtual().isEmAprovacao() 
        || roteiro.getStatusAtual().isAprovado() || roteiro.getStatusAtual().isRecusado())
            throw new IllegalParameterException("Erro: roteiro fora já processado!");
        
            
        if(roteiro.getStatusAtual().isEmAnalise() && !dados.avancaProcesso()) {
            
            roteiro.setStatusAtual(TipoStatusRoteiro.RECUSADO);
            historicoService.registrar(roteiro, roteiro.getStatusAtual(), dados.justificativa());

        }else{

            historicoService.registrar(roteiro, roteiro.getStatusAtual(), dados.justificativa());

            roteiro.setStatusAtual( TipoStatusRoteiro.toEnum(roteiro.getStatusAtual().getCod() + 1));

        }

        
        roteiroRepository.save(roteiro);

    }

    private Roteiro converteRoteiroDadosDTOParaRoteiro(RoteiroDadosDTO dados){
        Roteiro roteiro = new Roteiro();
        roteiro.setTitulo(dados.titulo());
        roteiro.setTexto(dados.texto());
        roteiro.setDataHoraEnvio(LocalDateTime.now());
        return roteiro;
    }

    private RoteiroDTO converteRoteiroParaRoteiroDTO(Cliente dadosCliente, Roteiro dados ){
        
        RoteiroDTO roteiro = new RoteiroDTO(
            dados.getId(), 
            dados.getDataHoraEnvio(),
            dados.getTitulo(),
            dados.getTexto(),
            clienteService.converteClienteParaClienteDTO(dadosCliente),
            dados.getStatusAtual().getDescricao(),
            historicoService.buscarHistorico(dados)
        );

        return roteiro;

    }

}
