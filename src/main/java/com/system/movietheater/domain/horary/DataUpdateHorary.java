package com.system.movietheater.domain.horary;

import com.system.movietheater.domain.movietheater.MovieTheater;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateHorary(
        @NotNull
        Long id,
        String day,
        String horary,
        @NotNull
        MovieTheater movieTheater) {
}
