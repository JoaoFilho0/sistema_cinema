package com.system.movietheater.domain.session;

public record DataRegisterSession(
    DataSession sessao,
    Long sala,
    Long filme

) {
}
