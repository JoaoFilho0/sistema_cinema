package com.system.movietheater.usercase.movie;

import com.system.movietheater.application.dto.movie.RegisterMovieDto;
import com.system.movietheater.application.dto.movie.UpdateMovieDto;
import com.system.movietheater.domain.model.Movie;
import com.system.movietheater.infrastructure.exceptions.MovieAlreadyExistsException;
import com.system.movietheater.infrastructure.exceptions.MovieNotFoundException;
import com.system.movietheater.infrastructure.persistence.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    private Movie movie;

    @Test
    void shouldSaveMovie() {
        var movie = new RegisterMovieDto("O Filme", 100);

        this.movie = this.movieService.registerMovie(movie);

        Assertions.assertEquals("O Filme", this.movie.getTitle());
        Assertions.assertNotNull(this.movie.getId());
    }

    @Test
    void shouldReturnMovieAlreadyExistsException() {
        var movie = new RegisterMovieDto("O Regresso", 100);

        assertThrows(MovieAlreadyExistsException.class, () -> {
            this.movieService.registerMovie(movie);
        });
    }

    @Test
    void shouldUpdateDataMovie() {
        var movieList = this.movieRepository.findAll();
        var updateMovie = new UpdateMovieDto(movieList.get(movieList.size()-1).getId(), "Novo Filme", 120);

        this.movie = this.movieService.updateMovie(updateMovie);

        Assertions.assertEquals("Novo Filme", this.movie.getTitle());
        Assertions.assertEquals(120, this.movie.getDuration());
        Assertions.assertNotNull(this.movie.getId());
    }

    @Test
    void shouldReturnMovieNotFound() {
        var updateMovie = new UpdateMovieDto(99999999L, "Title", 100);

        Assertions.assertThrows(MovieNotFoundException.class, () -> {
            this.movieService.updateMovie(updateMovie);
        });
    }

    @Test
    void shouldDeleteMovie() {
        var movie = this.movieRepository.findAll();
        var movieDelete = movie.get(movie.size() - 1);
        int amount = movie.size();

        movieService.deleteMovie(movieDelete.getId());

        movie = this.movieRepository.findAll();

        Assertions.assertNotEquals(amount, movie.size());
    }

    @Test
    void shouldReturnMovieNotFoundException() {
        Assertions.assertThrows(MovieNotFoundException.class, () -> {
            movieService.deleteMovie(99999999L);
        });
    }
}