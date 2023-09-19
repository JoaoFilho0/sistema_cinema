package com.system.movietheater.domain.horary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoraryRepository extends JpaRepository<Horary, Long> {
    List<Horary> findByMovieTheaterId(Long id);
}
