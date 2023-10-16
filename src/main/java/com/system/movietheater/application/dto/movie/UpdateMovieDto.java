package com.system.movietheater.application.dto.movie;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record UpdateMovieDto(
        @Schema(name = "id", description = "movie id", example = "1")
        @NotNull
        Long id,
        @Schema(name = "title", description = "movie title", example = "O Regresso")
        String title,
        @Schema(name = "duration", description = "movie duration", example = "100")
        int duration
) {
}
