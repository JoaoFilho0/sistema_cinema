package com.system.movietheater.domain.movie;

import com.system.movietheater.infra.exception.MovieAlreadyExistsException;
import com.system.movietheater.infra.exception.MovieNotFoundException;
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
