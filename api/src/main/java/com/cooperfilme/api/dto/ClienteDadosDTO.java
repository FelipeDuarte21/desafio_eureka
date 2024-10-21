package com.cooperfilme.api.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClienteDadosDTO(
    @NotNull(message = "Informe o nome do cliente")
    @Length(min = 3, message = "Digite o nome pelo menos de {min} caracteres")
    String nome,

    @NotNull(message = "Informe um email")
    @Email(message = "Email fora do formato")
    String email,

    @NotNull(message = "Infome um número para contato")
    @Pattern(regexp = "^(\\d{2})(9\\d{4}|\\d{4})\\d{4}$", message = "Número de telefone/celular inválido.")
    String telefone
) {}
