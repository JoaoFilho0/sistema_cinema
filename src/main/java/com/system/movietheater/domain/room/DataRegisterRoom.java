package com.system.movietheater.domain.room;

import com.system.movietheater.domain.movietheater.DataListingMovieTheater;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record DataRegisterRoom(
        @Schema(name = "number", description = "cinema room number", example = "1")
        @NotNull
        int number,
        @Schema(name = "seats", description = "cinema room seats", example = "1")
        @NotNull
        int seats,
        @Schema(name = "movieTheater", description = "movie theater")
        @NotNull
        DataListingMovieTheater movieTheater) {
}
