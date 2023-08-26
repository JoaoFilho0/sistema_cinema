package com.sistema.cinema.domain.cliente;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaCliente(
        @NotNull
        Long id,
        String nome,
        String email
) {
}
