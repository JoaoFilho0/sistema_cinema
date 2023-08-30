package com.system.movietheater.domain.session;

public record DataSession(
        Long id,
        String horario,
        int ingressos,
        float preco
) {
}
