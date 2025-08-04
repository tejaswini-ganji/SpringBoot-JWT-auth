package com.project.restapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.project.restapi.entities.ProjectEntity;
import com.project.restapi.models.GeneralResponse;
import com.project.restapi.models.ProjectModel;
import com.project.restapi.services.ProjectServices;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<GeneralResponse> getProjects() {
        return projectServices.getProjects();
    }

    @PostMapping("/add/project")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse> addProject(@RequestBody ProjectModel dto) {
    try {
            ProjectEntity savedProject = projectServices.addProject(dto);
            return ResponseEntity.ok(new GeneralResponse(true, "Project added successfully", savedProject));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new GeneralResponse(false, "Failed to add project: " + e.getMessage()));
        }   
   }

    @DeleteMapping("/delete/project")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse> deleteProject(@RequestParam Long id) {
        try {
            projectServices.deleteProject(id);
            return ResponseEntity.ok(new GeneralResponse(true, "Project deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new GeneralResponse(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new GeneralResponse(false, "Unexpected error: " + e.getMessage()));
        }
    }


    @PutMapping("/update/project/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse> updateProject(@PathVariable Long id, @RequestBody ProjectModel dto) {
        try {
            ProjectEntity updatedProject = projectServices.updateProject(id, dto);
            return ResponseEntity.ok(
                new GeneralResponse(true, "Project updated successfully", updatedProject)
            );
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new GeneralResponse(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new GeneralResponse(false, "Unexpected error: " + e.getMessage()));
        }
    }

}
