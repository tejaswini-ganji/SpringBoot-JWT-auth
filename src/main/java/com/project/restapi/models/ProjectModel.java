package com.project.restapi.models;

import java.time.LocalDate;
public class ProjectModel {
   

    private String name;
    private String description;
    private LocalDate deadline;
    private String managerName;

    public ProjectModel() {
    }

    public ProjectModel(String name, String description, LocalDate deadline, String managerName) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.managerName = managerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}



