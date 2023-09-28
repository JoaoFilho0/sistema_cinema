package com.system.movietheater.domain.usersession;

import com.system.movietheater.domain.session.DataUpdateSession;
import com.system.movietheater.domain.session.SessionRepository;
import com.system.movietheater.domain.user.User;
import com.system.movietheater.domain.user.UserRepository;
import com.system.movietheater.infra.exception.SessionNotFoundException;
import com.system.movietheater.infra.exception.UserNotFoundExcpetion;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class UserSessionService {

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    public UserSession registerUserSession(DataRegisterUserSession data) {
        var session = sessionRepository.findById(data.session().getId()).orElseThrow(SessionNotFoundException::new);
        var user = userRepository.findById(data.user().getId()).orElseThrow(UserNotFoundExcpetion::new);
        var userSession = new UserSession(new DataRegisterUserSession(user, session));

        userSession.setSession(session);

        userSessionRepository.save(userSession);

        session.setTickets(session.getTickets() - 1);
        session.updateData(new DataUpdateSession(session.getId(), session.getPrice()));

        return userSession;
    }

    public URI generateUri(UserSession userSession, UriComponentsBuilder uriBuilder) {
        return uriBuilder.path("usuario/sessao/{id}").buildAndExpand(userSession.getId()).toUri();
    }

    public User listingUserSessionPerUser(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundExcpetion::new);
    }
}
