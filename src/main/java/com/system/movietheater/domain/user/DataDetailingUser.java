package com.system.movietheater.domain.user;

import com.system.movietheater.domain.movietheater.MovieTheater;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record DataDetailingUser(
        @Schema(name = "id", description = "user id", example = "1")
        Long id,
        @Schema(name = "name", description = "user name", example = "John")
        String nome,
        @Schema(name = "email", description = "user email", example = "john@gmail.com")
        String email,
        @Schema(name = "movieTheater", description = "cinemas of a given user")
        List<MovieTheater> movieTheater,
        @Schema(name = "active", description = "User account status", example = "true")
        Boolean active
) {

    public DataDetailingUser(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getMovieTheater(), user.getActive());
    }

}
