package com.system.movietheater.domain.session;

import com.system.movietheater.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByDate(String date);

    List<Session> findByRoom(Room room);
}
