package com.system.movietheater.domain.usersession;

import com.system.movietheater.domain.session.DataSession;

public record DataRegisterUserSession(
        int check,
        Long usuario,
        DataSession sessao
) {
}
