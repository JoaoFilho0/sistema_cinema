package com.sistema.cinema.domain.sala;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalasRepository extends JpaRepository<Salas, Long> {
    @Override
    Page<Salas> findAll(Pageable paginacao);
}
