package com.system.movietheater.domain.address;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterAddress(
        @NotBlank
        String cidade,
        @NotBlank
        String bairro,
        @NotBlank
        String rua,
        String numero
) {
}
