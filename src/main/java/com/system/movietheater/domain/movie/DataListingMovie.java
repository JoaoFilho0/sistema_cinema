package com.system.movietheater.domain.movie;

public record DataListingMovie(Long id, String title, int duration) {
    public DataListingMovie(Movie movie) {
        this(movie.getId(), movie.getTitle(), movie.getDuration());
    }
}
