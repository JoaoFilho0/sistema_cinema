package com.system.movietheater.domain.user;

import com.system.movietheater.domain.movietheater.MovieTheater;

public record DataDetailingUser(Long id, String nome, String email, MovieTheater movieTheater) {

    public DataDetailingUser(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getMovieTheater());
    }

}
