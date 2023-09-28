package com.system.movietheater.domain.session;

import com.system.movietheater.domain.movie.MovieRepository;
import com.system.movietheater.domain.room.RoomRepository;
import com.system.movietheater.domain.session.validation.ValidationSession;
import com.system.movietheater.infra.exception.MovieNotFoundException;
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
