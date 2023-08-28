package com.sistema.cinema.domain.cliente;

import com.sistema.cinema.domain.cinema.Cinema;

public record DadosListagemCliente(Long id, String nome, String email, Cinema cinema) {

    public DadosListagemCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCinema());
    }

}
