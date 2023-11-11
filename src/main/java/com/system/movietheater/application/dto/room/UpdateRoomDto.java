package com.system.movietheater.application.dto.room;

import com.system.movietheater.application.dto.movietheater.ListingMovieTheaterDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record UpdateRoomDto(
        @Schema(name = "id", description = "room id", example = "1")
        @NotNull
        Long id,
        @Schema(name = "number", description = "cinema room number", example = "1")
        int number,
        @Schema(name = "seats", description = "cinema room seats", example = "1")
        int seats,
        @Schema(name = "movieTheater", description = "movie theater")
        @NotNull
        ListingMovieTheaterDto movieTheater
) {
}