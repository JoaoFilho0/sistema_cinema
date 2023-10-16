package com.system.movietheater.application.dto.session;

import com.system.movietheater.domain.model.Movie;
import com.system.movietheater.domain.model.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterSessionDto(
    @Schema(name = "date", description = "session date", example = "2023-10-26")
    @NotEmpty
    @NotBlank
    String date,
    @Schema(name = "horary", description = "session horary", example = "10:00-12:00")
    @NotEmpty
    @NotBlank
    String horary,
    @Schema(name = "price", description = "session price", example = "20.10")
    @NotNull
    float price,
    @Schema(name = "room", description = "session room")
    @NotNull
    Room room,
    @Schema(name = "movie", description = "session movie")
    @NotNull
    Movie movie

) {
}
