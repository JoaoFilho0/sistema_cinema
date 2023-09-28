package com.system.movietheater.domain.usersession;

import com.system.movietheater.domain.session.Session;
import com.system.movietheater.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record DataRegisterUserSession(
        @Schema(name = "user", description = "User id", example = "1")
        @NotEmpty
        User user,
        @Schema(name = "session", description = "Session id", example = "1")
        @NotEmpty
        Session session
) {
}
