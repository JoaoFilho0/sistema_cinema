package com.sistema.cinema.domain.sessao;

import com.sistema.cinema.domain.filme.Filme;
import com.sistema.cinema.domain.sala.Salas;

public record DadosCadastroSessao(
    String horario,
    int ingressos,
    float preco,
    Salas salas,
    Filme filme

) {
}
