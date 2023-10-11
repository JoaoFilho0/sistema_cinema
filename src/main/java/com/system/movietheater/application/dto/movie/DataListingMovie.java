package com.system.movietheater.application.dto.movie;

import com.system.movietheater.domain.model.Movie;

public record DataListingMovie(Long id, String title, int duration) {
    public DataListingMovie(Movie movie) {
        this(movie.getId(), movie.getTitle(), movie.getDuration());
    }
}
