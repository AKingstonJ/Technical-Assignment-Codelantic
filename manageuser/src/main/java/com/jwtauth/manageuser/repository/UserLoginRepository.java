package com.jwtauth.manageuser.repository;

import com.jwtauth.manageuser.entity.UserDetails;
import com.jwtauth.manageuser.entity.UserLoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginDetails,Long> {
    boolean existsByUsername(String userName);

    Optional<UserLoginDetails> findByUsername(String username);
}
