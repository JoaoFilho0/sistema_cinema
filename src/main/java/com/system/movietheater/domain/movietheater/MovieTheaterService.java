package com.system.movietheater.domain.movietheater;

import com.system.movietheater.domain.address.AddressRepository;
import com.system.movietheater.domain.user.ProfileUser;
import com.system.movietheater.domain.user.UserRepository;
import com.system.movietheater.infra.exception.AddressMovieTheaterAlreadyExistsException;
import com.system.movietheater.infra.exception.MovieTheaterNotFoundException;
import com.system.movietheater.infra.exception.NameMovieTheaterAlreadyRegisteredException;
import com.system.movietheater.infra.exception.UserNotFoundExcpetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class MovieTheaterService {

    @Autowired
    private MovieTheaterRepository movieTheaterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

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
        var movieTheater = movieTheaterRepository.findById(data.id()).orElseThrow(MovieTheaterNotFoundException::new);
        movieTheater.updateData(data);

        return movieTheater;
    }
}
