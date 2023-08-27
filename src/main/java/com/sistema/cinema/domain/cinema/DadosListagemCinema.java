package com.sistema.cinema.domain.cinema;

import com.sistema.cinema.domain.cliente.DadosListagemCliente;
import com.sistema.cinema.domain.endereco.Endereco;

public record DadosListagemCinema(Long id, String nome, Endereco endereco) {

    public DadosListagemCinema(Cinema cinema) {
        this(cinema.getId(), cinema.getNome(), cinema.getEndereco());
    }

}
