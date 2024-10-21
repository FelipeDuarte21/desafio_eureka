package com.cooperfilme.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.cooperfilme.api.entity.enums.TipoStatusRoteiro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class RegistroStatusRoteiro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private TipoStatusRoteiro status;

    @Column(columnDefinition = "text")
    private String justificativa;

    @ManyToOne
    private Roteiro roteiro;

}
