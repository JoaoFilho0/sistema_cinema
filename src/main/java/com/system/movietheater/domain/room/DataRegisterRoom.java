package com.system.movietheater.domain.room;

import com.system.movietheater.domain.movietheater.MovieTheater;
import jakarta.validation.constraints.NotNull;

public record DataRegisterRoom(
        @NotNull
        int number,
        @NotNull
        int seats,
        @NotNull
        MovieTheater movieTheater) {
}
