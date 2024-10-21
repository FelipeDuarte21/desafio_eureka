package com.cooperfilme.api.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Voto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean decisao;

    @ManyToOne
    private Roteiro roteiro;

    private String emailVotante;

    public boolean isAprovado(){
        return decisao.booleanValue() == true;
    }

    public boolean isReprovado(){
        return decisao.booleanValue() == false;
    }

}
