package com.system.movietheater.domain.movie;

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
        var movie = movieRepository.getReferenceById(data.id());
        movie.updateData(data);

        return movie;
    }
}