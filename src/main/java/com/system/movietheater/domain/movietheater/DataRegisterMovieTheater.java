package com.system.movietheater.domain.movietheater;

import com.system.movietheater.domain.address.DataRegisterAddress;

public record DataRegisterMovieTheater(
        String nome,
        DataRegisterAddress endereco,
        Long cliente
) {
}
