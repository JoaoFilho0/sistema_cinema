package com.system.movietheater.domain.horary;

import com.system.movietheater.domain.movietheater.MovieTheater;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterHorary(
        @NotBlank
        String day,
        @NotBlank
        String horary,
        @NotNull
        MovieTheater movieTheater) {
}
