package com.system.movietheater.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterAddress(
        @NotBlank
        String city,
        @NotBlank
        String district,
        @NotBlank
        String street,
        @NotBlank
        @NotNull
        String number
) {
}
