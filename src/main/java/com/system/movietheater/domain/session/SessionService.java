package com.system.movietheater.domain.session;

import com.system.movietheater.domain.room.RoomRepository;
import com.system.movietheater.infra.exception.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private RoomRepository roomRepository;

    public Session registerSession(DataRegisterSession data) {
        var room = roomRepository.findById(data.room().getId()).orElseThrow(RoomNotFoundException::new);

        var session = new Session(data);

        session.setTickets(room.getSeats());

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
