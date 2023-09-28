package com.system.movietheater.domain.session;

import io.swagger.v3.oas.annotations.media.Schema;

public record DataUpdateSession(
        @Schema(name = "id", description = "session id", example = "1")
        Long id,
        @Schema(name = "price", description = "session price", example = "20.10")
        float price
) {
}
