package com.system.movietheater.domain.usersession;

import com.system.movietheater.domain.session.DataListingSession;
import com.system.movietheater.domain.user.DataListingUser;

public record DataReturnUserSession (
        DataListingUser user,
        DataListingSession session
) {
}
