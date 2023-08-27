package com.sistema.cinema.domain.cliente;

public record DadosDetalhamentoCliente(Long id, String nome, String email) {

    public DadosDetalhamentoCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail());
    }

}
