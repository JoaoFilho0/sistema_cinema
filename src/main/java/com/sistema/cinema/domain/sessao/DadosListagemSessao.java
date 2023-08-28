package com.sistema.cinema.domain.sessao;

import com.sistema.cinema.domain.filme.Filme;
import com.sistema.cinema.domain.sala.DadosListagemSala;
import com.sistema.cinema.domain.sala.Sala;

public record DadosListagemSessao(Long id, String horario, int ingressos, float preco, Sala sala, Filme filme) {

    public DadosListagemSessao(Sessao sessao) {
        this(sessao.getId(), sessao.getHorario(), sessao.getIngressos(), sessao.getPreco(), sessao.getSala(), sessao.getFilme());
    }
}
