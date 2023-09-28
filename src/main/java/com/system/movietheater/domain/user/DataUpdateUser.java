package com.system.movietheater.domain.user;

import com.system.movietheater.domain.movietheater.MovieTheater;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record DataUpdateUser(
        @Schema(name = "id", description = "user id", example = "1")
        @NotNull
        Long id,
        @Schema(name = "name", description = "user name", example = "John")
        String name,
        @Schema(name = "email", description = "user email", example = "john@gmail.com")
        String email
) {
}
