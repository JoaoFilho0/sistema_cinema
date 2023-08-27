package com.sistema.cinema.domain.sala;

import com.sistema.cinema.domain.cinema.Cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {
    @Override
    Page<Sala> findAll(Pageable paginacao);
}
