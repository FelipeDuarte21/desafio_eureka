package com.cooperfilme.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooperfilme.api.entity.RegistroStatusRoteiro;
import com.cooperfilme.api.entity.Roteiro;

import java.util.List;


public interface RegistroStatusRoteiroRepository extends JpaRepository<RegistroStatusRoteiro, Long> {

    List<RegistroStatusRoteiro> findByRoteiro(Roteiro roteiro);

}
