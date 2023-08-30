package com.system.movietheater.domain.session;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
    @Override
    Page<Session> findAll(Pageable paginacao);
}
