package com.cooperfilme.api.dto;

import jakarta.validation.constraints.NotNull;

public record RoteiroDadosDTO(

    @NotNull(message = "Informe um título")
    String titulo,

    @NotNull(message = "Informe o roteiro")
    String texto,

    @NotNull(message = "Informe os dados do cliente")
    ClienteDadosDTO cliente

) {}
