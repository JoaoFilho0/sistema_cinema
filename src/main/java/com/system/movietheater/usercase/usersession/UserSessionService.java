package com.system.movietheater.usercase.usersession;

import com.system.movietheater.application.dto.usersession.DataRegisterUserSession;
import com.system.movietheater.domain.model.UserSession;
import com.system.movietheater.application.dto.session.DataUpdateSession;
import com.system.movietheater.infrastructure.persistence.repository.SessionRepository;
import com.system.movietheater.application.dto.user.DataListingUser;
import com.system.movietheater.domain.model.User;
import com.system.movietheater.infrastructure.persistence.repository.UserRepository;
import com.system.movietheater.infrastructure.exceptions.SessionNotFoundException;
import com.system.movietheater.infrastructure.exceptions.UserNotFoundExcpetion;
import com.system.movietheater.infrastructure.persistence.repository.UserSessionRepository;
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
        var user = userRepository.findById(data.user().id()).orElseThrow(UserNotFoundExcpetion::new);
        var userSession = new UserSession(new DataRegisterUserSession(new DataListingUser(user), session));

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
