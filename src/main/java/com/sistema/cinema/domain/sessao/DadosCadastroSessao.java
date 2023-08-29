package com.sistema.cinema.domain.sessao;

import com.sistema.cinema.domain.filme.Filme;
import com.sistema.cinema.domain.sala.Salas;

public record DadosCadastroSessao(
    DadosSessao sessao,
    Long sala,
    Long filme

) {
}
