package com.system.movietheater.domain.user;

import io.swagger.v3.oas.annotations.media.Schema;

public record DataListingUser(
        @Schema(name = "id", description = "user id", example = "1")
        Long id,
        @Schema(name = "name", description = "user name", example = "John")
        String name,
        @Schema(name = "email", description = "user email", example = "john@gmail.com")
        String email
) {

    public DataListingUser(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }

}
