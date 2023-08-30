package com.system.movietheater.domain.room;

import jakarta.validation.constraints.NotNull;

public record DataRegisterRoom(
        @NotNull
        int numero,
        @NotNull
        int assentos,
        @NotNull
        Long cinema) {
}
