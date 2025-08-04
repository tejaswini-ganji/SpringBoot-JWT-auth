package com.project.restapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.project.restapi.entities.Project;
import com.project.restapi.services.ProjectServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class ProjectController {

    @Autowired
    private ProjectServices projectServices;


    @GetMapping("/getProjects")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<Project> getProjects() {
        return projectServices.getProjects();
    }

    @PostMapping("/add/project")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Project addProject(@RequestBody Project project) {
        return projectServices.addProject(project);
    }

    @DeleteMapping("/delete/project")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteProject(@RequestParam Long id) {
         return projectServices.deleteProject(id);
    }


    @PutMapping("/update/project/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
        return projectServices.updateProject(id, project);
    }

}
