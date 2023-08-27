package com.sistema.cinema.domain.cinema;

import com.sistema.cinema.domain.endereco.Endereco;

public record DadosDetalhamentoCinema(Long id, String nome, Endereco endereco) {

    public DadosDetalhamentoCinema(Cinema cinema) {
        this(cinema.getId(), cinema.getNome(), cinema.getEndereco());
    }

}
