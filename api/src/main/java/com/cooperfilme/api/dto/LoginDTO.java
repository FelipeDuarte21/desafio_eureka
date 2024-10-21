package com.cooperfilme.api.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginDTO(

    @NotNull(message = "Informe o campo email")
	@Email(message = "o valor informado está fora do padrão para email")
    String email,

    @NotNull(message = "Informe o campo senha")
	@Length(min=4, max=8, message = "O campo senha deve ter entre {min} a {max} caracteres")
    String senha
) {}
