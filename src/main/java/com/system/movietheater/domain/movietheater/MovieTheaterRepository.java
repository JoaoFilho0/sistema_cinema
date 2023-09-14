package com.system.movietheater.domain.movietheater;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieTheaterRepository extends JpaRepository<MovieTheater, Long> {
//    @Query(value = "select * from cinema c " +
//            "inner join usuario u on u.fk_cinema_id=c.cin_id " +
//            "inner join endereco end on end.end_id=c.fk_endereco_id " +
//            "right join salas sa on sa.cin_id=c.cin_id " +
//            "right join horario hor on hor.fk_cinema_id=c.cin_id " +
//            "where u.usu_ativo", nativeQuery = true)
//    List<MovieTheater> findByAllMovieTheater();

}
