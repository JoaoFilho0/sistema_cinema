package com.system.movietheater.application.dto.movietheater;

import com.system.movietheater.domain.model.Address;
import com.system.movietheater.domain.model.MovieTheater;
import jakarta.validation.constraints.NotNull;

public record DataListingMovieTheater(
        @NotNull
        Long id,
        String name,
        Address address) {

    public DataListingMovieTheater(MovieTheater movieTheater) {
        this(movieTheater.getId(), movieTheater.getName(), movieTheater.getAddress());
    }

}
