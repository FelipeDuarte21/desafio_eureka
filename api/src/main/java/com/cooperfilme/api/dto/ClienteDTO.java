package com.cooperfilme.api.dto;

public record ClienteDTO(
    Long id,
    String nome,
    String email,
    String telefone
) {}
