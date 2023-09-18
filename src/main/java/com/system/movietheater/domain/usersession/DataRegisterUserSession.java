package com.system.movietheater.domain.usersession;

import com.system.movietheater.domain.session.DataListingSession;
import com.system.movietheater.domain.session.DataSession;

public record DataRegisterUserSession(
        Long user,
        DataListingSession session
) {
}
