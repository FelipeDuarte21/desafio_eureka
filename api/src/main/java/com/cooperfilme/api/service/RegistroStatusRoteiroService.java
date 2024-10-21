package com.cooperfilme.api.service;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.cooperfilme.api.dto.RegistroStatusRoteiroDTO;
import com.cooperfilme.api.entity.RegistroStatusRoteiro;
import com.cooperfilme.api.entity.Roteiro;
import com.cooperfilme.api.entity.enums.TipoStatusRoteiro;
import com.cooperfilme.api.repository.RegistroStatusRoteiroRepository;

@Service
public class RegistroStatusRoteiroService {

    private RegistroStatusRoteiroRepository registroStatusRoteiroRepository;

    public RegistroStatusRoteiroService(RegistroStatusRoteiroRepository registroStatusRoteiroRepository){
        this.registroStatusRoteiroRepository = registroStatusRoteiroRepository;
    }

    public void registrar(Roteiro roteiro, TipoStatusRoteiro stauts, String justificativa){

        RegistroStatusRoteiro registro = new RegistroStatusRoteiro();
        registro.setDataHora(LocalDateTime.now());
        registro.setJustificativa(justificativa);
        registro.setStatus(stauts);
        registro.setRoteiro(roteiro);

        registroStatusRoteiroRepository.save(registro);

    }

    public List<RegistroStatusRoteiroDTO> buscarHistorico(Roteiro roteiro){

        List<RegistroStatusRoteiro> historico = registroStatusRoteiroRepository.findByRoteiro(roteiro);

        return historico.stream()
            .map(registro -> converteRegistroStatusRoteiroParaRegistroStatusRoteiroDTO(registro))
                .collect(Collectors.toList());

    }

    private RegistroStatusRoteiroDTO converteRegistroStatusRoteiroParaRegistroStatusRoteiroDTO(RegistroStatusRoteiro dados){
        RegistroStatusRoteiroDTO registro = new RegistroStatusRoteiroDTO(
            dados.getDataHora(),
            dados.getStatus().getDescricao() , 
            dados.getJustificativa()
        );
        return registro;
    }

}
