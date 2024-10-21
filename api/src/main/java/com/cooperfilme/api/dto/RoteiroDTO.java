package com.cooperfilme.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;


public record RoteiroDTO(
    Long id,
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime dataHoraEnvio,
    String titulo,
    String texto,
    ClienteDTO cliente,
    String statusAtual,
    List<RegistroStatusRoteiroDTO> historicoStatus
) {}
