package com.system.movietheater.domain.movietheater;

import com.system.movietheater.domain.address.DataUpdateAddress;
import jakarta.validation.constraints.NotNull;

public record DataUpdateMovieTheater(
        @NotNull
        Long id,
        String name,
        DataUpdateAddress address
) {
}
