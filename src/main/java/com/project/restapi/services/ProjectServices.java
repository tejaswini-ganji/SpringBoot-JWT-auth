package com.project.restapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.restapi.entities.ProjectEntity;
import com.project.restapi.models.GeneralResponse;
import com.project.restapi.models.ProjectModel;
import com.project.restapi.repositories.ProjectRepository;

@Service
public class ProjectServices {
    @Autowired
    private ProjectRepository projectRepository;

    public ResponseEntity<GeneralResponse> getProjects() {
         try {
            List<ProjectEntity> projects = projectRepository.findAll();
            return ResponseEntity.ok(new GeneralResponse(true, "Projects fetched successfully", projects));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new GeneralResponse(false, "Error fetching projects: " + e.getMessage()));
        }
    }

    public ProjectEntity addProject(ProjectModel dto) {
        ProjectEntity project = new ProjectEntity();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setDeadline(dto.getDeadline());
        project.setManagerName(dto.getManagerName());
        
        return projectRepository.save(project);
    }


    public void deleteProject(Long id) {
            projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        projectRepository.deleteById(id);
    }

    public ProjectEntity updateProject(Long id, ProjectModel dto) {
        ProjectEntity existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));

        existingProject.setName(dto.getName());
        existingProject.setDescription(dto.getDescription());
        existingProject.setDeadline(dto.getDeadline());
        existingProject.setManagerName(dto.getManagerName());

        return projectRepository.save(existingProject);
    }

}
