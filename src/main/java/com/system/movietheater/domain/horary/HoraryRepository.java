package com.system.movietheater.domain.horary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HoraryRepository extends JpaRepository<Horary, Long> {
    List<Horary> findByMovieTheaterId(Long id);

    @Query(value = "SELECT hor_id, hor_dia, hor_horario, fk_cinema_id FROM horario WHERE fk_cinema_id=:id " +
            "AND hor_dia=:day", nativeQuery = true)
    Horary findMovieTheaterAndDay(Long id, String day);
}
