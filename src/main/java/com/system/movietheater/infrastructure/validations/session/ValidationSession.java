package com.system.movietheater.infrastructure.validations.session;

import com.system.movietheater.domain.model.Session;

public interface ValidationSession {
    public void validate(Session data);
}
