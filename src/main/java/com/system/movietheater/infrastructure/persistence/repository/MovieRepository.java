package com.system.movietheater.infrastructure.persistence.repository;

import com.system.movietheater.domain.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByTitleAndDuration(String title, int duration);

    @Query(value = "SELECT f.fil_id, f.fil_nome, f.fil_duracao FROM filmes f", nativeQuery = true)
    List<Movie> findAllMovies();
}
