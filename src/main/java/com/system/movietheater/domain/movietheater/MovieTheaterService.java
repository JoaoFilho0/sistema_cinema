package com.system.movietheater.domain.movietheater;

import com.system.movietheater.domain.user.User;
import com.system.movietheater.domain.user.UserRepository;
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
        var user = userRepository.findById(data.user()).orElseThrow(() -> new EntityNotFoundException("User not exists"));

        var movieTheaterData = movieTheaterRepository.save(movieTheater);
        user.setMovieTheater(movieTheaterData);

        userRepository.save(user);

        return movieTheater;
    }

    public URI generateUri(MovieTheater movieTheater, UriComponentsBuilder uriBuilder) {
        return uriBuilder.path("cinema/{id}").buildAndExpand(movieTheater.getId()).toUri();
    }

    public List<MovieTheater> listMovieTheaters() {
        List<User> users = userRepository.findByActiveTrueAndMovieTheaterNotNull();

        return users.stream().map(MovieTheater::new).toList();
    }

    public MovieTheater selectMovieTheater(Long id) {
        return movieTheaterRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Movie theater not exists"));
    }

    public MovieTheater updateMovieTheater(DataUpdateMovieTheater data) {
        var movieTheater = movieTheaterRepository.getReferenceById(data.id());
        movieTheater.updateData(data);

        return movieTheater;
    }
}
