package com.system.movietheater.domain.movietheater;

import com.system.movietheater.domain.address.DataUpdateAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record DataUpdateMovieTheater(
        @Schema(name = "id", description = "movie theater id", example = "1")
        @NotNull
        Long id,
        @Schema(name = "name", description = "movie theater name", example = "Cinema")
        String name,
        @Schema(name = "address", description = "movie theater address")
        DataUpdateAddress address
) {
}
