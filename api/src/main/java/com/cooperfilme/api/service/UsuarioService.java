package com.cooperfilme.api.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cooperfilme.api.dto.UsuarioDadosDTO;
import com.cooperfilme.api.dto.UsuarioDTO;
import com.cooperfilme.api.entity.Usuario;
import com.cooperfilme.api.entity.enums.TipoCargo;
import com.cooperfilme.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, 
        BCryptPasswordEncoder bCryptPasswordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UsuarioDTO cadastrar(UsuarioDadosDTO dados) {

        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(dados.email());

        if(optUsuario.isPresent()) throw new IllegalArgumentException("Erro: usuário já cadastrado!"); 

        Usuario usuario = converteUsuarioDadosDTOParaUsuario(dados);

        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));

        this.usuarioRepository.save(usuario);

        return converteUsuarioParaUsuarioDTO(usuario);
        
    }

    public Usuario buscarPorId(Long id){

        Optional<Usuario> optUsuario = usuarioRepository.findById(id);

        return optUsuario.get();

    }

    private Usuario converteUsuarioDadosDTOParaUsuario(UsuarioDadosDTO dados){
        Usuario usuario = new Usuario();
        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        usuario.setSenha(dados.senha());
        usuario.setCargo(TipoCargo.toEnum(dados.cargo()));
        return usuario;
    }

    private UsuarioDTO converteUsuarioParaUsuarioDTO(Usuario dados){
        UsuarioDTO usuario = new UsuarioDTO(dados.getId(), 
        dados.getNome(), dados.getEmail(), dados.getSenha());
        return usuario;
    }


}
