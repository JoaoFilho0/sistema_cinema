package com.system.movietheater.application.dto.usersession;

import com.system.movietheater.application.dto.session.ListingSessionDto;
import com.system.movietheater.application.dto.user.ListingUserDto;

public record ReturnUserSessionDto(
        ListingUserDto user,
        ListingSessionDto session
) {
}
