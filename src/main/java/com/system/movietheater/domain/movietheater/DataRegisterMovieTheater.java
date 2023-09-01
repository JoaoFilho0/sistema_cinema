package com.system.movietheater.domain.movietheater;

import com.system.movietheater.domain.address.DataRegisterAddress;

public record DataRegisterMovieTheater(
        String name,
        DataRegisterAddress address,
        Long user
) {
}
