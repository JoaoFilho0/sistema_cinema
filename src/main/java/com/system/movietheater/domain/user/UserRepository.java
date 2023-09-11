package com.system.movietheater.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u.usu_id, u.usu_email, u.usu_nome FROM User")
//    List<DataListingUser> findAllUser();
}
