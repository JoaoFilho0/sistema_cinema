package com.system.movietheater.domain.address;

import jakarta.validation.constraints.NotBlank;

public record DataUpdateAddress(
        @NotBlank
        Long id,
        String city,
        String district,
        String street,
        String number
) {
}
