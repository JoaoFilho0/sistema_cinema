package com.sistema.cinema.domain.sessao;

import com.sistema.cinema.domain.filme.Filme;
import com.sistema.cinema.domain.sala.Sala;

public record DadosCadastroSessao(
    String horario,
    int ingressos,
    float preco,
    Sala sala,
    Filme filme

) {
}
