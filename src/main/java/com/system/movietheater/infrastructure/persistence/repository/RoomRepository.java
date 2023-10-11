package com.system.movietheater.infrastructure.persistence.repository;

import com.system.movietheater.domain.model.MovieTheater;
import com.system.movietheater.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByNumberAndMovieTheater(int number, MovieTheater movieTheater);

    Room findByNumberAndSeatsAndMovieTheater(int number, int seats, MovieTheater movieTheater);
}
