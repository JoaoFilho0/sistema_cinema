package com.system.movietheater.domain.user;

import com.system.movietheater.domain.movietheater.MovieTheater;

public record DataListingUser(Long id, String nome, String email) {

    public DataListingUser(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }

}
