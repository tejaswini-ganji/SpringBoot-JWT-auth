package com.project.restapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.restapi.entities.Project;
import com.project.restapi.repositories.ProjectRepository;

@Service
public class ProjectServices {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    public String deleteProject(Long id) {
        try{
            projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error deleting project: " + e.getMessage());
        }
        projectRepository.deleteById(id);
        return "Project deleted successfully";
    }

    public Project updateProject(Long id, Project project) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        existingProject.setName(project.getName());
        existingProject.setDescription(project.getDescription());
        existingProject.setDeadline(project.getDeadline());
        existingProject.setManagerName(project.getManagerName());
        return projectRepository.save(existingProject);
    }
}
