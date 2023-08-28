package com.sistema.cinema.domain.sessao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    @Override
    Page<Sessao> findAll(Pageable paginacao);
}
