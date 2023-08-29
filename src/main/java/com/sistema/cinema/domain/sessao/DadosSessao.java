package com.sistema.cinema.domain.sessao;

public record DadosSessao(
        Long id,
        String horario,
        int ingressos,
        float preco
) {
}
