package com.system.movietheater.usercase.movie;

import com.system.movietheater.application.dto.movie.DataListingMovie;
import com.system.movietheater.application.dto.movie.DataRegisterMovie;
import com.system.movietheater.application.dto.movie.DataUpdateMovie;
import com.system.movietheater.domain.model.Movie;
import com.system.movietheater.infrastructure.exceptions.MovieAlreadyExistsException;
import com.system.movietheater.infrastructure.exceptions.MovieNotFoundException;
import com.system.movietheater.infrastructure.persistence.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie registerMovie(DataRegisterMovie data) {
        if (movieRepository.findByTitleAndDuration(data.title(), data.duration()) != null) {
            throw new MovieAlreadyExistsException();
        }

        var movie = new Movie(data);

        movieRepository.save(movie);

        return movie;
    }

    public URI generateUri(Movie movie, UriComponentsBuilder uriBuilder){
        return uriBuilder.path("cinema/filme/{id}").buildAndExpand(movie.getId()).toUri();
    }

    public List<DataListingMovie> listMovies(Pageable pagination) {
        return movieRepository.findAll(pagination).stream().map(DataListingMovie::new).toList();
    }

    public Movie updateMovie(DataUpdateMovie data) {
        var movie = movieRepository.findById(data.id()).orElseThrow(MovieNotFoundException::new);

        if (movieRepository.findByTitleAndDuration(data.title(), data.duration()) != null) {
            throw new MovieAlreadyExistsException();
        }

        movie.updateData(data);

        return movie;
    }
}
