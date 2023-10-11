package com.system.movietheater.infrastructure.persistence.repository;

import com.system.movietheater.domain.model.Room;
import com.system.movietheater.domain.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByDate(String date);

    List<Session> findByRoom(Room room);
}
