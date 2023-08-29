package com.sistema.cinema.domain.usuario;

import com.sistema.cinema.domain.cinema.Cinema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
    @NotBlank
    String nome,
    @NotBlank
    @Email
    String email,
    @NotBlank
    String senha,
    Cinema cinema
) {
}
