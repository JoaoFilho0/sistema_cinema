package com.system.movietheater.domain.movietheater;

import com.system.movietheater.domain.address.AddressRepository;
import com.system.movietheater.domain.horary.HoraryService;
import com.system.movietheater.domain.room.RoomService;
import com.system.movietheater.domain.session.Session;
import com.system.movietheater.domain.session.SessionRepository;
import com.system.movietheater.domain.user.ProfileUser;
import com.system.movietheater.domain.user.UserRepository;
import com.system.movietheater.domain.usersession.UserSession;
import com.system.movietheater.domain.usersession.UserSessionRepository;
import com.system.movietheater.infra.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MovieTheaterService {

    @Autowired
    private MovieTheaterRepository movieTheaterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private HoraryService horaryService;

    public MovieTheater register(DataRegisterMovieTheater data) {
        var user = userRepository.findById(data.user()).orElseThrow(UserNotFoundExcpetion::new);

        if (movieTheaterRepository.findByName(data.name()) != null) {
            throw new NameMovieTheaterAlreadyRegisteredException("Name movie theater already exists");
        }

        var address = addressRepository.findByNumberAndStreetAndDistrictAndCity(data.address().number(), data.address().street(), data.address().district(), data.address().city());
        if (address != null) throw new AddressMovieTheaterAlreadyExistsException("Address movie theater already exists");

        var movieTheater = new MovieTheater(data);

        movieTheater.setUser(user);

        movieTheaterRepository.save(movieTheater);

        user.setType(List.of(ProfileUser.ROLE_MOVIETHEATER));

        userRepository.save(user);

        return movieTheater;
    }

    public URI generateUri(MovieTheater movieTheater, UriComponentsBuilder uriBuilder) {
        movieTheaterRepository.findById(movieTheater.getId()).orElseThrow(MovieTheaterNotFoundException::new);

        return uriBuilder.path("cinema/{id}").buildAndExpand(movieTheater.getId()).toUri();
    }

    public List<DataListingMovieTheater> listMovieTheaters() {
        return movieTheaterRepository.findAllMovieTheaterActive().stream().map(DataListingMovieTheater::new).toList();
    }

    public MovieTheater selectMovieTheater(Long id) {
        return movieTheaterRepository.findById(id).orElseThrow(MovieTheaterNotFoundException::new);
    }

    public MovieTheater updateMovieTheater(DataUpdateMovieTheater data) {
        var address = addressRepository.findByNumberAndStreetAndDistrictAndCity(data.address().number(), data.address().street(), data.address().district(), data.address().city());
        if (!Objects.equals(address.getId(), data.address().id())) throw new AddressMovieTheaterAlreadyExistsException("Address movie theater already exists");

        var movieTheater = movieTheaterRepository.findById(data.id()).orElseThrow(MovieTheaterNotFoundException::new);
        movieTheater.updateData(data);

        return movieTheater;
    }

    public void deleteMovieTheater(Long id) {
        var movieTheater = movieTheaterRepository.findById(id).orElseThrow(MovieTheaterNotFoundException::new);
        var address = addressRepository.findById(movieTheater.getAddress().getId()).orElseThrow(AddressNotFoundException::new);

        if (!movieTheater.getRooms().isEmpty()) {

            List<List<Session>> sessions = new ArrayList<List<Session>>();
            List<List<UserSession>> userSessions = new ArrayList<List<UserSession>>();

            movieTheater.getRooms().forEach(room -> {
                sessions.add(sessionRepository.findByRoom(room));
            });

            if (sessions != null) {
                sessions.forEach(sessions1 -> {
                    sessions1.forEach( session -> {
                        userSessions.add(userSessionRepository.findBySession(session));
                    });
                });

                userSessions.forEach(userSessions1 -> {
                    userSessions1.forEach(userSession -> {
                        userSessionRepository.delete(userSession);
                    });
                });

                sessions.forEach(sessions1 -> {
                    sessions1.forEach( session -> {
                        sessionRepository.delete(session);
                    });
                });
            }

            movieTheater.getRooms().forEach(room -> roomService.deleteRoom(room.getId()));

            if (!movieTheater.getHoraries().isEmpty()){
                movieTheater.getHoraries().forEach(horary -> horaryService.deleteHorary(horary.getId()));
            }
        }

        movieTheaterRepository.delete(movieTheater);
        addressRepository.delete(address);
    }
}
