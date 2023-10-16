package com.system.movietheater.application.dto.user;

import com.system.movietheater.domain.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record ListingUserDto(
        @Schema(name = "id", description = "user id", example = "1")
        Long id,
        @Schema(name = "name", description = "user name", example = "John")
        String name,
        @Schema(name = "email", description = "user email", example = "john@gmail.com")
        String email
) {

    public ListingUserDto(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }

}
