package com.sistema.cinema.domain.sessao;

import com.sistema.cinema.domain.filme.Filme;
import com.sistema.cinema.domain.sala.Salas;

public record DadosListagemSessao(Long id, String horario, int ingressos, float preco) {

    public DadosListagemSessao(Sessao sessao) {
        this(sessao.getId(), sessao.getHorario(), sessao.getIngressos(), sessao.getPreco());
    }
}
