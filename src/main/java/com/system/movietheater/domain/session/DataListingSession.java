package com.system.movietheater.domain.session;

public record DataListingSession(Long id, String horario, int ingressos, float preco) {

    public DataListingSession(Session session) {
        this(session.getId(), session.getHorario(), session.getTickets(), session.getPrice());
    }
}
