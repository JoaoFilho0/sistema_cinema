package com.sistema.cinema.domain.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCliente(
    @NotBlank
    String cli_nome,
    @NotBlank
    @Email
    String cli_email,
    @NotBlank
    String cli_senha
) {
}
