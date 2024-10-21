package com.cooperfilme.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooperfilme.api.entity.Cliente;
import com.cooperfilme.api.entity.Roteiro;
import com.cooperfilme.api.entity.enums.TipoStatusRoteiro;


public interface RoteiroRepository extends JpaRepository<Roteiro, Long>{

    List<Roteiro> findByCliente(Cliente cliente);

    List<Roteiro> findByStatusAtual(TipoStatusRoteiro statusAtual);

}
