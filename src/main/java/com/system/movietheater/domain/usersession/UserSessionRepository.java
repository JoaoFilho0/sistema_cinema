package com.system.movietheater.domain.usersession;

import com.system.movietheater.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    List<DataListingUserSession> findByUser(User user);
}
