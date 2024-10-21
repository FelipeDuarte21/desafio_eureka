package com.cooperfilme.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cooperfilme.api.controller.exception.AuthorizationException;
import com.cooperfilme.api.controller.exception.ObjectBadRequestException;
import com.cooperfilme.api.controller.exception.ObjectNotFoundException;
import com.cooperfilme.api.dto.RoteiroDTO;
import com.cooperfilme.api.dto.RoteiroDadosDTO;
import com.cooperfilme.api.dto.StatusDadosDTO;
import com.cooperfilme.api.dto.VotoDadosDTO;
import com.cooperfilme.api.security.UsuarioDetalhe;
import com.cooperfilme.api.service.RoteiroService;
import com.cooperfilme.api.service.exception.IllegalParameterException;
import com.cooperfilme.api.service.exception.ObjectNotFoundFromParameterException;
import com.cooperfilme.api.service.exception.UserAuthorizationException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/roteiro")
public class RoteiroController {

    private RoteiroService roteiroService;

    public RoteiroController(RoteiroService roteiroService){
        this.roteiroService = roteiroService;
    }

    @PostMapping
    public ResponseEntity<RoteiroDTO> cadastrar(@RequestBody @Valid RoteiroDadosDTO dados,
        UriComponentsBuilder uriBuilder){

        try{

            RoteiroDTO roteiro = this.roteiroService.cadastrar(dados);

            var uri = uriBuilder.path("/api/v1/roteiro/{id}").buildAndExpand(roteiro.id()).toUri();

            return ResponseEntity.created(uri).body(roteiro);

        }catch(Exception ex){
            throw new ObjectBadRequestException(ex.getMessage());
        }

    }

    @GetMapping("/cliente")
    public ResponseEntity<List<RoteiroDTO>> buscarRoteirosCliente(@RequestParam(name = "nome") String nome, 
        @RequestParam(name = "email") String email){

        try{

            List<RoteiroDTO> roteiros = roteiroService.buscarRoteirosCliente(nome, email);

            return ResponseEntity.ok().body(roteiros);

        }catch(ObjectNotFoundFromParameterException ex){
            throw new ObjectBadRequestException(ex.getMessage());
        }
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoteiroDTO> buscarPorId(@PathVariable(name = "id")Long id){

        try{

            RoteiroDTO roteiro = roteiroService.buscarPorId(id);

            return ResponseEntity.ok().body(roteiro);

        }catch(ObjectNotFoundFromParameterException ex){
            throw new ObjectNotFoundException(ex.getMessage());
        }

    }

    @GetMapping("/usuario")
    public ResponseEntity<List<RoteiroDTO>> buscarRoteirosUsuario(
        @AuthenticationPrincipal UsuarioDetalhe usuarioLogado){

        List<RoteiroDTO> roteiros = roteiroService.buscarRoteirosUsuario(usuarioLogado);

        return ResponseEntity.ok().body(roteiros);

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> mudarStatusRoteiro(@PathVariable(name = "id") Long id,
        @RequestBody @Valid StatusDadosDTO dados,
        @AuthenticationPrincipal UsuarioDetalhe usuarioLogado){

        try{

            roteiroService.mudarStatusRoteiro(id, dados, usuarioLogado);

            return ResponseEntity.ok().build();

        }catch(UserAuthorizationException ex){
            throw new AuthorizationException(ex.getMessage());

        }catch(ObjectNotFoundFromParameterException ex){
            throw new ObjectNotFoundException(ex.getMessage());
            
        }catch(IllegalParameterException ex){
            throw new ObjectBadRequestException(ex.getMessage());

        }

    }

    @PutMapping("/{id}/votacao")
    public ResponseEntity<?> votarRoteiro(@PathVariable Long id,
        @RequestBody @Valid VotoDadosDTO voto,
        @AuthenticationPrincipal UsuarioDetalhe usuarioLogado){

        try{

            roteiroService.votarRoteiro(id, voto, usuarioLogado);

            return ResponseEntity.ok().build();

        }catch(UserAuthorizationException ex){
            throw new AuthorizationException(ex.getMessage());

        }catch(ObjectNotFoundFromParameterException ex){
            throw new ObjectNotFoundException(ex.getMessage());

        }catch(IllegalParameterException ex){
            throw new ObjectBadRequestException(ex.getMessage());

        }

    }

}
