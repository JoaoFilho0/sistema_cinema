package com.system.movietheater.domain.movietheater;

import com.system.movietheater.domain.user.User;
import com.system.movietheater.domain.user.UserRepository;
import com.system.movietheater.infra.exception.UserNotFoundExcpetion;
import jakarta.persistence.EntityNotFoundException;
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

    public MovieTheater register(DataRegisterMovieTheater data) {
        var movieTheater = new MovieTheater(data);
        var user = userRepository.findById(data.user()).orElseThrow(() -> new UserNotFoundExcpetion("User not found"));

        var movieTheaterData = movieTheaterRepository.save(movieTheater);
        user.setMovieTheater(movieTheaterData);

        userRepository.save(user);

        return movieTheater;
    }

    public URI generateUri(MovieTheater movieTheater, UriComponentsBuilder uriBuilder) {
        movieTheaterRepository.findById(movieTheater.getId()).orElseThrow(() -> new EntityNotFoundException("Movie theater not found"));

        return uriBuilder.path("cinema/{id}").buildAndExpand(movieTheater.getId()).toUri();
    }

    public List<MovieTheater> listMovieTheaters() {
        List<User> users = userRepository.findByActiveTrueAndMovieTheaterNotNull();

        return users.stream().map(MovieTheater::new).toList();
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
