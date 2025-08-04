package com.project.restapi.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.restapi.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
