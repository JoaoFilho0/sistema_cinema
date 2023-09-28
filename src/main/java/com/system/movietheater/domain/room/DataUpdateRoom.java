package com.system.movietheater.domain.room;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DataUpdateRoom(
        @Schema(name = "id", description = "room id", example = "1")
        @NotEmpty
        @NotNull
        Long id,
        @Schema(name = "number", description = "cinema room number", example = "1")
        int number,
        @Schema(name = "seats", description = "cinema room seats", example = "1")
        int seats
) {
}
