package com.cooperfilme.api.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.cooperfilme.api.entity.Roteiro;
import com.cooperfilme.api.entity.Voto;
import com.cooperfilme.api.repository.VotoRepository;

@Service
public class VotoService {

    private VotoRepository votoRepository;

    public VotoService(VotoRepository votoRepository){
        this.votoRepository = votoRepository;
    }

    public Voto registrarVoto(Roteiro roteiro, Boolean decisao, String emailVotante){
        Voto voto = new Voto();
        voto.setDecisao(decisao);
        voto.setRoteiro(roteiro);
        voto.setEmailVotante(emailVotante);
        votoRepository.save(voto);
        return voto;
    }

    public boolean verificarSeUsuarioJaVotou(List<Voto> votos, String emailUsuario){
        for(Voto voto: votos){
            if(voto.getEmailVotante().equals(emailUsuario)) return true;
        }
        return false;
    }

}
