package com.sistema.cinema.domain.filme;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaFilme(
        @NotNull
        Long id,
        String titulo,
        int duracao
) {
}
