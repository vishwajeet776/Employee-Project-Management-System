package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    public ProjectDTO createProject(ProjectDTO dto);

    public List<ProjectDTO> getAllProjects();

    public ProjectDTO getProjectById(Long id);

    public ProjectDTO updateProject(Long id, ProjectDTO dto);

    public void deleteProject(Long id);
}
