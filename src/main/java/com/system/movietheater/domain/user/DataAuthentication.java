package com.system.movietheater.domain.user;

import jakarta.validation.constraints.Email;

public record DataAuthentication(
        @Email
        String email,
        String password
) {
}
