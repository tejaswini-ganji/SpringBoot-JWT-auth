package com.project.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.restapi.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    // This interface will automatically provide CRUD operations for Project entities
    // Additional custom query methods can be defined here if needed

}
