package com.system.movietheater.infrastructure.persistence.repository;

import com.system.movietheater.domain.model.Session;
import com.system.movietheater.domain.model.User;
import com.system.movietheater.application.dto.usersession.DataListingUserSession;
import com.system.movietheater.domain.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    List<DataListingUserSession> findByUser(User user);

    List<UserSession> findBySession(Session session);
}
