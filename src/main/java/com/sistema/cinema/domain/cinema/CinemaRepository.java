package com.sistema.cinema.domain.cinema;

import com.sistema.cinema.domain.cliente.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    @Override
    Page<Cinema> findAll(Pageable paginacao);
}
