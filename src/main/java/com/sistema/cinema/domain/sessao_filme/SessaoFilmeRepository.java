package com.sistema.cinema.domain.sessao_filme;

import com.sistema.cinema.domain.sessao_salas.SessaoSalasRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoFilmeRepository extends JpaRepository<SessaoFilme, Long> {
}
