package com.sistema.cinema.domain.sala;

import com.sistema.cinema.domain.cinema.Cinema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroSala(
        @NotBlank
        int numero,
        @NotBlank
        int assentos,
        @NotNull
        @Valid
        Cinema cinema) {
}
