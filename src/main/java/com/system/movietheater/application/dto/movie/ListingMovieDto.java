package com.system.movietheater.application.dto.movie;

import com.system.movietheater.domain.model.Movie;

public record ListingMovieDto(Long id, String title, int duration) {
    public ListingMovieDto(Movie movie) {
        this(movie.getId(), movie.getTitle(), movie.getDuration());
    }
}
