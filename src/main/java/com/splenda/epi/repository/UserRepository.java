package com.splenda.epi.repository;

import java.util.Optional;

import com.splenda.epi.entities.core.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);

    Boolean existsByName(String username);

    Boolean existsByEmailAddress(String email);
}