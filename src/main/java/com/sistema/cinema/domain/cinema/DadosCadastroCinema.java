package com.sistema.cinema.domain.cinema;

import com.sistema.cinema.domain.endereco.Endereco;

public record DadosCadastroCinema(
        String nome,
        Endereco endereco
) {
}
