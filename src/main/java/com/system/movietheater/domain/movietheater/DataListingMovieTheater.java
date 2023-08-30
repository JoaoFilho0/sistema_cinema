package com.system.movietheater.domain.movietheater;

import com.system.movietheater.domain.address.Address;

public record DataListingMovieTheater(Long id, String nome, Address address) {

    public DataListingMovieTheater(MovieTheater movieTheater) {
        this(movieTheater.getId(), movieTheater.getNome(), movieTheater.getAddress());
    }

}
