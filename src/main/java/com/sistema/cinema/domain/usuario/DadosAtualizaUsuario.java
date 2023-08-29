package com.sistema.cinema.domain.usuario;

import com.sistema.cinema.domain.cinema.Cinema;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaUsuario(
        @NotNull
        Long id,
        String nome,
        String email,
        Cinema cinema
) {
}
