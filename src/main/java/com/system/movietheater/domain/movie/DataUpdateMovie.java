package com.system.movietheater.domain.movie;

import jakarta.validation.constraints.NotNull;

public record DataUpdateMovie(
        @NotNull
        Long id,
        String title,
        int duration
) {
}
