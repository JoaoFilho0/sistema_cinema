package com.system.movietheater.domain.movie;

public record DataListingMovie(Long id, String titulo, int duracao) {
    public DataListingMovie(Movie movie) {
        this(movie.getId(), movie.getTitle(), movie.getDuration());
    }
}
