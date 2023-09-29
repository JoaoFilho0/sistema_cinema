package com.system.movietheater.domain.room;

import com.system.movietheater.domain.movietheater.MovieTheater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByNumberAndMovieTheater(int number, MovieTheater movieTheater);

    Room findByNumberAndSeatsAndMovieTheater(int number, int seats, MovieTheater movieTheater);
}
