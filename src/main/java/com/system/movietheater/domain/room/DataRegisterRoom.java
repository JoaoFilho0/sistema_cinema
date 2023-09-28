package com.system.movietheater.domain.room;

import com.system.movietheater.domain.movietheater.MovieTheater;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DataRegisterRoom(
        @Schema(name = "number", description = "cinema room number", example = "1")
        @NotEmpty
        @NotNull
        int number,
        @Schema(name = "seats", description = "cinema room seats", example = "1")
        @NotEmpty
        @NotNull
        int seats,
        @Schema(name = "movie theater", description = "movie theater")
        @NotNull
        MovieTheater movieTheater) {
}
