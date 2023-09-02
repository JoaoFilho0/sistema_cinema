package com.system.movietheater.domain.movietheater;

import com.system.movietheater.domain.address.Address;

public record DataListingMovieTheater(Long id, String name, Address address) {

    public DataListingMovieTheater(MovieTheater movieTheater) {
        this(movieTheater.getId(), movieTheater.getName(), movieTheater.getAddress());
    }

}
