package com.system.movietheater.domain.room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Override
    Page<Room> findAll(Pageable paginacao);
}
