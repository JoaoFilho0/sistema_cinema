package com.sistema.cinema.domain.cliente;

import com.sistema.cinema.domain.cinema.Cinema;

public record DadosDetalhamentoCliente(Long id, String nome, String email, Cinema cinema) {

    public DadosDetalhamentoCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCinema());
    }

}
