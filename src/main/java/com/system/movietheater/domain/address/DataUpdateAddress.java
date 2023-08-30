package com.system.movietheater.domain.address;

import jakarta.validation.constraints.NotBlank;

public record DataUpdateAddress(
        @NotBlank
        Long id,
        String cidade,
        String bairro,
        String rua,
        String numero
) {
}
