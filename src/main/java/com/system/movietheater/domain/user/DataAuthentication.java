package com.system.movietheater.domain.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DataAuthentication(
        @Schema(name = "email", description = "user email", example = "john@gmail.com")
        @NotEmpty
        @NotBlank
        @Email
        String email,
        @Schema(name = "password", description = "user password", example = "1234")
        @NotEmpty
        @NotBlank
        String password
) {
}
