package com.system.movietheater.domain.session;

import com.system.movietheater.domain.movie.Movie;
import com.system.movietheater.domain.room.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataRegisterSession(
    @Schema(name = "date", description = "session date", example = "2023-10-26")
    @NotEmpty
    @NotBlank
    String date,
    @Schema(name = "horary", description = "session horary", example = "10:00-12:00")
    @NotEmpty
    @NotBlank
    String horary,
    @Schema(name = "price", description = "session price", example = "20.10")
    @NotEmpty
    @NotBlank
    float price,
    @Schema(name = "room", description = "session room")
    @NotEmpty
    @NotNull
    Room room,
    @Schema(name = "movie", description = "session movie")
    @NotEmpty
    @NotNull
    Movie movie

) {
}
