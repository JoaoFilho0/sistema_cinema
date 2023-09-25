package com.system.movietheater.domain.movietheater;

import com.system.movietheater.domain.user.ProfileUser;
import com.system.movietheater.domain.user.User;
import com.system.movietheater.domain.user.UserRepository;
import com.system.movietheater.infra.exception.AddressMovieTheaterAlreadyExistsException;
import com.system.movietheater.infra.exception.NameMovieTheaterAlreadyRegisteredException;
import com.system.movietheater.infra.exception.UserNotFoundExcpetion;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Service
public class MovieTheaterService {

    @Autowired
    private MovieTheaterRepository movieTheaterRepository;

    @Autowired
    private UserRepository userRepository;

    public MovieTheater register(DataRegisterMovieTheater data) {
        System.out.println("chegou");
        var user = userRepository.findById(data.user()).orElseThrow(() -> new UserNotFoundExcpetion("User not found"));

        if (movieTheaterRepository.findByName(data.name()) != null) {
            throw new NameMovieTheaterAlreadyRegisteredException("Name movie theater already exists");
        }

        var address = movieTheaterRepository.findAddressNumber(data.address().number());
        if (address != null) {
            if (Objects.equals(address.getStreet(), data.address().street())) {
                throw new AddressMovieTheaterAlreadyExistsException("Address movie theater already exists");
            }
        }

        var movieTheater = new MovieTheater(data);

        movieTheater.setUser(user);

        movieTheaterRepository.save(movieTheater);

        user.setType(List.of(ProfileUser.ROLE_MOVIETHEATER));

        userRepository.save(user);

        return movieTheater;
    }

    public URI generateUri(MovieTheater movieTheater, UriComponentsBuilder uriBuilder) {
        movieTheaterRepository.findById(movieTheater.getId()).orElseThrow(() -> new EntityNotFoundException("Movie theater not found"));

        return uriBuilder.path("cinema/{id}").buildAndExpand(movieTheater.getId()).toUri();
    }

    public List<MovieTheater> listMovieTheaters() {
        return movieTheaterRepository.findAllMovieTheaterActive();
    }

    public MovieTheater selectMovieTheater(Long id) {
        return movieTheaterRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Movie theater not found"));
    }

    public MovieTheater updateMovieTheater(DataUpdateMovieTheater data) {
        var movieTheater = movieTheaterRepository.findById(data.id()).orElseThrow(() -> new EntityNotFoundException(("Movie theater not found")));
        movieTheater.updateData(data);

        return movieTheater;
    }
}
