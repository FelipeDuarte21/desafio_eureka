package com.cooperfilme.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record RegistroStatusRoteiroDTO(
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime dataHora,
    String status,
    String justificativa
) {}
