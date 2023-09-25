package com.system.movietheater.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);

    @Query( value = "SELECT * FROM usuario u WHERE u.usu_email = :email", nativeQuery = true)
    User findEmail(String email);

    List<DataListingUser> findByActiveTrue();
}
