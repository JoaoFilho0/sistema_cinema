package com.system.movietheater.domain.room;

import jakarta.validation.constraints.NotNull;

public record DataRegisterRoom(
        @NotNull
        int number,
        @NotNull
        int seats,
        @NotNull
        Long movieTheater) {
}
