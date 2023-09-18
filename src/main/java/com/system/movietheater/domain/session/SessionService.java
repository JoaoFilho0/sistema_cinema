package com.system.movietheater.domain.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public Session registerSession(DataRegisterSession data) {
        var session = new Session(data);

        sessionRepository.save(session);

        return session;
    }

    public URI generateUri(Session session, UriComponentsBuilder uriBuilder) {
        return uriBuilder.path("cinema/sessao/{id}").buildAndExpand(session.getId()).toUri();
    }

    public List<DataListingSession> list() {
        return sessionRepository.findAll().stream().map(DataListingSession::new).toList();
    }
}
