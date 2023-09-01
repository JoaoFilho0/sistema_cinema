package com.system.movietheater.domain.session;

public record DataRegisterSession(
    DataSession session,
    Long room,
    Long movie

) {
}
