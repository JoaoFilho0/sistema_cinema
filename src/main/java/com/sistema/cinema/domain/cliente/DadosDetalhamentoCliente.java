package com.sistema.cinema.domain.cliente;

public record DadosDetalhamentoCliente(Long id, String nome, String email) {

    public DadosDetalhamentoCliente(Cliente cliente) {
        this(cliente.getCli_id(), cliente.getCli_nome(), cliente.getCli_email());
    }

}
