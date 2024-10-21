package com.cooperfilme.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.cooperfilme.api.entity.enums.TipoStatusRoteiro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Roteiro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "text")
    private String texto;

    private LocalDateTime dataHoraEnvio;

    @Enumerated(EnumType.STRING)
    private TipoStatusRoteiro statusAtual;

    @OneToMany(mappedBy = "roteiro")
    private List<RegistroStatusRoteiro> historicoStatus = new ArrayList<>();

    @OneToMany(mappedBy = "roteiro")
    private List<Voto> votos = new ArrayList<>();

    @ManyToOne
    private Cliente cliente;

    public void addVoto(Voto voto){
        this.votos.add(voto);
    }

}
