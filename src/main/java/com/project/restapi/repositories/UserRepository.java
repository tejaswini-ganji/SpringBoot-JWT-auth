package com.project.restapi.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.restapi.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
