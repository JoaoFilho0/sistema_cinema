package com.system.movietheater.domain.session;

import com.system.movietheater.domain.movie.Movie;
import com.system.movietheater.domain.room.Room;

public record DataListingSession(Long id, String date, String horary, int tickets, float price, Movie movie, Room room) {

    public DataListingSession(Session session) {
        this(session.getId(), session.getDate(), session.getHorary(), session.getTickets(), session.getPrice(), session.getMovie(), session.getRoom());
    }
}
