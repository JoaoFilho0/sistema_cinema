package com.sistema.cinema.domain.sala;

import com.sistema.cinema.domain.cinema.Cinema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroSala(
        @NotNull
        int numero,
        @NotNull
        int assentos,
        @NotNull
        Long cinema) {
}
