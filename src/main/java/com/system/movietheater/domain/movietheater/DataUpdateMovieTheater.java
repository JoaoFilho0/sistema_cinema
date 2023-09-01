package com.system.movietheater.domain.movietheater;

import com.system.movietheater.domain.address.DataUpdateAddress;
import jakarta.validation.constraints.NotBlank;

public record DataUpdateMovieTheater(
        @NotBlank
        Long id,
        String name,
        DataUpdateAddress address
) {
}
