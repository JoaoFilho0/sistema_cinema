package com.sistema.cinema.domain.cliente;

import com.sistema.cinema.domain.cinema.Cinema;
import com.sistema.cinema.domain.cinema.DadosAtualizaCinema;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaCliente(
        @NotNull
        Long id,
        String nome,
        String email,
        Cinema cinema
) {
}
