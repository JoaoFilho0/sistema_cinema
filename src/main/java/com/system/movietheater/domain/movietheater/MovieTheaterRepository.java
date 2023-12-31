package com.system.movietheater.domain.movietheater;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieTheaterRepository extends JpaRepository<MovieTheater, Long> {
    @Query(value = "SELECT *" +
            "FROM cinema c " +
            "LEFT JOIN usuario u ON u.usu_id=c.cin_id " +
            "WHERE u.usu_ativo = true",
    nativeQuery = true)
    List<MovieTheater> findAllMovieTheaterActive();

    MovieTheater findByName(String name);
}
