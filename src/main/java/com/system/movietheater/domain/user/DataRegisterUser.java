package com.system.movietheater.domain.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record DataRegisterUser(
    @Schema(name = "name", description = "User name", example = "John")
    @NotEmpty
    @NotBlank
    String name,
    @Schema(name = "email", description = "User email", example = "john@gmail.com")
    @NotBlank
    @Email
    String email,
    @Schema(name = "password", description = "User password", example = "1234")
    @NotBlank
    String password
) {
}
