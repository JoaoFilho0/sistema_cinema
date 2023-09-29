package com.system.movietheater.domain.usersession;

import com.system.movietheater.domain.session.Session;
import com.system.movietheater.domain.user.DataListingUser;
import com.system.movietheater.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DataRegisterUserSession(
        @Schema(name = "user", description = "User id")
        @NotNull
        DataListingUser user,
        @Schema(name = "session", description = "Session id")
        @NotNull
        Session session
) {
}
