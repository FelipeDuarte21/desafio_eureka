package com.cooperfilme.api.dto;

public record UsuarioDTO(
    Long id,
    String nome,
    String email,
    String senha
) {

}
