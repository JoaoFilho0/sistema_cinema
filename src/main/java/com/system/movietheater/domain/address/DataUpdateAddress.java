package com.system.movietheater.domain.address;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record DataUpdateAddress(
        @NotBlank
        Long id,
        @Schema(name = "city", description = "city related to cinema", example = "cidade")
        String city,
        @Schema(name = "district", description = "district related to cinema", example = "bairro")
        String district,
        @Schema(name = "street", description = "street related to cinema", example = "rua")
        String street,
        @Schema(name = "number", description = "number related to cinema", example = "1452")
        String number
) {
}
