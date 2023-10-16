package com.system.movietheater.application.dto.movietheater;

import com.system.movietheater.application.dto.address.UpdateAddressDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record UpdateMovieTheaterDto(
        @Schema(name = "id", description = "movie theater id", example = "1")
        @NotNull
        Long id,
        @Schema(name = "name", description = "movie theater name", example = "Cinema")
        String name,
        @Schema(name = "address", description = "movie theater address")
        UpdateAddressDto address
) {
}
