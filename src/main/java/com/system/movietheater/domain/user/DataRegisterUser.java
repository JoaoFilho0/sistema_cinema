package com.system.movietheater.domain.user;

import com.system.movietheater.domain.movietheater.DataRegisterMovieTheater;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataRegisterUser(
    @NotBlank
    String nome,
    @NotBlank
    @Email
    String email,
    @NotBlank
    String senha,
    DataRegisterMovieTheater cinema
) {
}
