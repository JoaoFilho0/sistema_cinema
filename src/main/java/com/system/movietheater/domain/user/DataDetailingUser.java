package com.system.movietheater.domain.user;

import com.system.movietheater.domain.movietheater.MovieTheater;

import java.util.List;

public record DataDetailingUser(Long id, String nome, String email, List<MovieTheater> movieTheater, Boolean active) {

    public DataDetailingUser(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getMovieTheater(), user.getActive());
    }

}
