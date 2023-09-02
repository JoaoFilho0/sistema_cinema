package com.system.movietheater.domain.session;

public record DataSession(
        Long id,
        String horary,
        int tickets,
        float price
) {
}
