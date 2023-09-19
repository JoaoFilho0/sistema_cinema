package com.system.movietheater.domain.usersession;

import com.system.movietheater.domain.session.Session;
import com.system.movietheater.domain.user.User;

public record DataRegisterUserSession(
        User user,
        Session session
) {
}
