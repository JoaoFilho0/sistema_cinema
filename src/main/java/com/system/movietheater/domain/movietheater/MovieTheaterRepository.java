package com.system.movietheater.domain.movietheater;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieTheaterRepository extends JpaRepository<MovieTheater, Long> {
    @Override
    Page<MovieTheater> findAll(Pageable paginacao);
}
