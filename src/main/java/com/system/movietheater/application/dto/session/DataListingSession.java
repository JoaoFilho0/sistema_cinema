package com.system.movietheater.application.dto.session;

import com.system.movietheater.domain.model.Movie;
import com.system.movietheater.domain.model.Room;
import com.system.movietheater.domain.model.Session;

public record DataListingSession(Long id, String date, String horary, int tickets, float price, Movie movie, Room room) {

    public DataListingSession(Session session) {
        this(session.getId(), session.getDate(), session.getHorary(), session.getTickets(), session.getPrice(), session.getMovie(), session.getRoom());
    }
}
