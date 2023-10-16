package com.system.movietheater.application.dto.usersession;

import com.system.movietheater.domain.model.Session;
import com.system.movietheater.application.dto.user.ListingUserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record RegisterUserSessionDto(
        @Schema(name = "user", description = "User id")
        @NotNull
        ListingUserDto user,
        @Schema(name = "session", description = "Session id")
        @NotNull
        Session session
) {
}
