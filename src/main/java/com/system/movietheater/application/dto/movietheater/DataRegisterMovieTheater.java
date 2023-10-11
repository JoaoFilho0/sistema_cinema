package com.system.movietheater.application.dto.movietheater;

import com.system.movietheater.application.dto.address.DataRegisterAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DataRegisterMovieTheater(
        @Schema(name = "name", description = "Name of the cinema", example = "Cinema")
        @NotEmpty
        @NotBlank
        String name,
        @Schema(name = "address", description = "Cinema address")
        @NotNull
        DataRegisterAddress address,
        @Schema(name = "user", description = "user id", example = "1")
        @NotNull
        Long user
) {
}
