package com.system.movietheater.domain.session;

import com.system.movietheater.domain.movie.Movie;
import com.system.movietheater.domain.room.Room;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataRegisterSession(
    @NotNull
    String date,
    @NotNull
    String horary,
    @NotNull
    float price,
    @NotNull
    Room room,
    @NotNull
    Movie movie

) {
}
