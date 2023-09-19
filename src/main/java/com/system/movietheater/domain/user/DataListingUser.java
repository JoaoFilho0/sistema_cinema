package com.system.movietheater.domain.user;

public record DataListingUser(Long id, String name, String email) {

    public DataListingUser(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }

}
