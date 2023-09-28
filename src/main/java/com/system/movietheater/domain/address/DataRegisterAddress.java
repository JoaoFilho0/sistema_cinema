package com.system.movietheater.domain.address;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record DataRegisterAddress(
        @Schema(name = "city", description = "city related to cinema", example = "cidade")
        @NotEmpty
        @NotBlank
        String city,
        @Schema(name = "district", description = "district related to cinema", example = "bairro")
        @NotEmpty
        @NotBlank
        String district,
        @Schema(name = "street", description = "street related to cinema", example = "rua")
        @NotEmpty
        @NotBlank
        String street,
        @Schema(name = "number", description = "number related to cinema", example = "1452")
        @NotEmpty
        @NotBlank
        String number
) {
}
