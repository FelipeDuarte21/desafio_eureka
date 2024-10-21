package com.cooperfilme.api.dto;

public record TokenDTO(
    String token,
	String tipo,
	Long idUsuario,
	String cargo,
	String email,
	String nome
) {}
