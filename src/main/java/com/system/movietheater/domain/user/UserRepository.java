package com.system.movietheater.domain.user;

import com.system.movietheater.domain.movietheater.DataListingMovieTheater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);
    List<DataListingUser> findByActiveTrue();
    List<User> findByActiveTrueAndMovieTheaterNotNull();
}
