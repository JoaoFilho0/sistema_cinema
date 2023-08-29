package com.sistema.cinema.domain.sala;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroSalas(
        @NotNull
        int numero,
        @NotNull
        int assentos,
        @NotNull
        Long cinema) {
}
