package com.cooperfilme.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooperfilme.api.dto.ClienteDTO;
import com.cooperfilme.api.dto.ClienteDadosDTO;
import com.cooperfilme.api.entity.Cliente;
import com.cooperfilme.api.repository.ClienteRepository;
import com.cooperfilme.api.service.exception.ObjectNotFoundFromParameterException;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrar(ClienteDadosDTO dados){

        Optional<Cliente> optCliente = clienteRepository.findByNomeAndEmail(dados.nome(), dados.email());

        if(optCliente.isPresent()) return optCliente.get();

        Cliente cliente = converteClienteDadosDTOParaCliente(dados);

        clienteRepository.save(cliente);

        return cliente;

    }

    public Cliente buscarCliente(String nome, String email){

        Optional<Cliente> optCliente = clienteRepository.findByNomeAndEmail(nome, email);

        if(optCliente.isPresent()) return optCliente.get();

        throw new ObjectNotFoundFromParameterException("Erro: Cliente não encontrado a partir do nome e email informado");

    }

    private Cliente converteClienteDadosDTOParaCliente(ClienteDadosDTO dados){
        Cliente cliente = new Cliente();
        cliente.setNome(dados.nome());
        cliente.setEmail(dados.email());
        cliente.setTelefone(dados.telefone());
        return cliente;
    }

    public ClienteDTO converteClienteParaClienteDTO(Cliente dados){
        ClienteDTO cliente = new ClienteDTO(
            dados.getId(), 
            dados.getNome(), 
            dados.getEmail(), 
            dados.getTelefone()
        );
        return cliente;
    }

}
