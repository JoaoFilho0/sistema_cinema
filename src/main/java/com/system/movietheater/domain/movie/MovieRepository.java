package com.system.movietheater.domain.movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByTitleAndDuration(String title, int duration);
}
