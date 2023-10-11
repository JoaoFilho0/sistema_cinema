package com.system.movietheater.application.dto.usersession;

import com.system.movietheater.application.dto.session.DataListingSession;
import com.system.movietheater.application.dto.user.DataListingUser;

public record DataReturnUserSession (
        DataListingUser user,
        DataListingSession session
) {
}
