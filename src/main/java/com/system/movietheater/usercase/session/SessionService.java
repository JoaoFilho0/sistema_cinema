package com.system.movietheater.usercase.session;

import com.system.movietheater.application.dto.session.DataListingSession;
import com.system.movietheater.application.dto.session.DataRegisterSession;
import com.system.movietheater.domain.model.Session;
import com.system.movietheater.infrastructure.persistence.repository.MovieRepository;
import com.system.movietheater.infrastructure.persistence.repository.RoomRepository;
import com.system.movietheater.infrastructure.validations.session.ValidationSession;
import com.system.movietheater.infrastructure.exceptions.MovieNotFoundException;
import com.system.movietheater.infrastructure.exceptions.RoomNotFoundException;
import com.system.movietheater.infrastructure.persistence.repository.SessionRepository;
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

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private List<ValidationSession> validations;

    public Session registerSession(DataRegisterSession data) {
        var room = roomRepository.findById(data.room().getId()).orElseThrow(RoomNotFoundException::new);

        movieRepository.findById(data.movie().getId()).orElseThrow(MovieNotFoundException::new);

        var session = new Session(data);
        session.setRoom(room);

        validations.forEach(validation -> validation.validate(session));

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
