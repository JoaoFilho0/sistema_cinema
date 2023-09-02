package com.system.movietheater.domain.user;

import com.system.movietheater.domain.movietheater.MovieTheater;
import jakarta.validation.constraints.NotNull;

public record DataUpdateUser(
        @NotNull
        Long id,
        String name,
        String email,
        MovieTheater movieTheater
) {
}
