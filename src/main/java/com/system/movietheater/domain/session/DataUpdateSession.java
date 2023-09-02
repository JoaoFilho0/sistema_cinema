package com.system.movietheater.domain.session;

public record DataUpdateSession(
        Long id,
        int tickets,
        float price
) {
}
