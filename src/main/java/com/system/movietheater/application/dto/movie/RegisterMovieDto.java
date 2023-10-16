package com.system.movietheater.application.dto.movie;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterMovieDto(
        @Schema(name = "title", description = "movie title", example = "O Regresso")
        @NotEmpty
        @NotBlank
        String title,
        @Schema(name = "duration", description = "movie duration", example = "100")
        @NotNull
        int duration
) {
}
