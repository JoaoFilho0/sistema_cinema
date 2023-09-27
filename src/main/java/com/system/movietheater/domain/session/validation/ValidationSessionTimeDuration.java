package com.system.movietheater.domain.session.validation;

import com.system.movietheater.domain.movie.Movie;
import com.system.movietheater.domain.movie.MovieRepository;
import com.system.movietheater.domain.session.Session;
import com.system.movietheater.infra.exception.MovieNotFoundException;
import com.system.movietheater.infra.exception.SessionDurationInvalidException;
import com.system.movietheater.infra.exception.SessionHoraryInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ValidationSessionTimeDuration implements ValidationSession{

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ValidationSessionHorary validationSessionHorary;

    public void validate(Session data) {
        validationSessionHorary.validate(data);

        Movie movie = movieRepository.findById(data.getMovie().getId()).orElseThrow(MovieNotFoundException::new);

        int movieMinutes = movie.getDuration() % 60;
        int movieHours = (movie.getDuration() - movieMinutes) / 60;

        String[] sessionDuration = data.getHorary().split("-");
        List<Integer> sessionInit = Arrays.stream(sessionDuration[0].split(":")).map(Integer::parseInt).toList();
        List<Integer> sessionEnd = Arrays.stream(sessionDuration[1].split(":")).map(Integer::parseInt).toList();

        if (sessionEnd.get(0) < (sessionInit.get(0) + movieHours)) {
            throw new SessionDurationInvalidException();
        } else if (sessionEnd.get(0) == (sessionInit.get(0) + movieHours)) {
            if (sessionEnd.get(1) < (sessionInit.get(1) + movieMinutes)) {
                throw new SessionDurationInvalidException();
            }
        }
    }
}
