package com.system.movietheater.application.dto.horary;

import com.system.movietheater.application.dto.movietheater.ListingMovieTheaterDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterHoraryDto(
        @Schema(name = "day", description = "day of week", example = "Segunda-feira")
        @NotEmpty
        @NotBlank
        String day,
        @Schema(name = "horary", description = "opening hours", example = "09:00-18:00")
        @NotEmpty
        @NotBlank
        String horary,
        @Schema(name = "movieTheater", description = "movie theater")
        @NotNull
        ListingMovieTheaterDto movieTheater) {
}
