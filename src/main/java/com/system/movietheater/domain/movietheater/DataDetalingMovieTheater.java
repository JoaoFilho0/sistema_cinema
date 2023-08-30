package com.system.movietheater.domain.movietheater;

import com.system.movietheater.domain.address.Address;

public record DataDetalingMovieTheater(Long id, String name, Address address) {

    public DataDetalingMovieTheater(MovieTheater movieTheater) {
        this(movieTheater.getId(), movieTheater.getName(), movieTheater.getAddress());
    }

}
