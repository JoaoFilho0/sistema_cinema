package com.sistema.cinema.domain.cinema;

import com.sistema.cinema.domain.cliente.DadosDetalhamentoCliente;
import com.sistema.cinema.domain.endereco.DadosCadastroEndereco;

public record DadosCadastroCinema(
        String nome,
        DadosCadastroEndereco endereco,
        Long cliente
) {
}
