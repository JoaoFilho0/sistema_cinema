package com.system.movietheater.domain.session;

import com.system.movietheater.domain.movie.Movie;
import com.system.movietheater.domain.room.Room;

public record DataRegisterSession(
    String horary,
    float price,
    int tickets,
    Room room,
    Movie movie

) {
}
