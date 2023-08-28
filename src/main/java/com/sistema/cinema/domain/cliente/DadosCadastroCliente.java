package com.sistema.cinema.domain.cliente;

import com.sistema.cinema.domain.cinema.Cinema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCliente(
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
