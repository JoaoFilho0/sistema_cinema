package com.system.movietheater.application.dto.room;

import com.system.movietheater.application.dto.movietheater.ListingMovieTheaterDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record RegisterRoomDto(
        @Schema(name = "number", description = "cinema room number", example = "1")
        @NotNull
        int number,
        @Schema(name = "seats", description = "cinema room seats", example = "1")
        @NotNull
        int seats,
        @Schema(name = "movieTheater", description = "movie theater")
        @NotNull
        ListingMovieTheaterDto movieTheater) {
}
