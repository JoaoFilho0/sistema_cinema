package com.system.movietheater.domain.horary;

import com.system.movietheater.domain.movietheater.MovieTheater;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DataUpdateHorary(
        @Schema(name = "id", description = "horary id", example = "1")
        @NotEmpty
        @NotNull
        Long id,
        @Schema(name = "day", description = "day of week", example = "Segunda-feira")
        String day,
        @Schema(name = "day", description = "opening hours", example = "09:00-18:00")
        String horary
) {
}
