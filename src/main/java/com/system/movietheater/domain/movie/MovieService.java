package com.system.movietheater.domain.movie;

import com.system.movietheater.infra.exception.MovieAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
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

    public List<Movie> listMovies(Pageable pagination) {
        return movieRepository.findAll(pagination).stream().toList();
    }

    public Movie updateMovie(DataUpdateMovie data) {
        var movie = movieRepository.findById(data.id()).orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        movie.updateData(data);

        return movie;
    }
}
