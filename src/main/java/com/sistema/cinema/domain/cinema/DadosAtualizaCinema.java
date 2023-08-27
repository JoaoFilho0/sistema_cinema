package com.sistema.cinema.domain.cinema;

import com.sistema.cinema.domain.endereco.DadosAtualizaEndereco;
import com.sistema.cinema.domain.endereco.Endereco;
import jakarta.validation.constraints.NotBlank;

public record DadosAtualizaCinema(
        @NotBlank
        Long id,
        String nome,
        DadosAtualizaEndereco endereco
) {
}
