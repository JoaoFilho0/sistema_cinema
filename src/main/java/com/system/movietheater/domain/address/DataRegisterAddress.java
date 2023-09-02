package com.system.movietheater.domain.address;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterAddress(
        @NotBlank
        String city,
        @NotBlank
        String district,
        @NotBlank
        String street,
        String number
) {
}
