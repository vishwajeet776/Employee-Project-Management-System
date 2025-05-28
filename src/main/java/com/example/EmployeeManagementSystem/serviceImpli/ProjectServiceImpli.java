package com.example.EmployeeManagementSystem.serviceImpli;

import com.example.EmployeeManagementSystem.Repository.ProjectRepository;
import com.example.EmployeeManagementSystem.dto.ProjectDTO;
import com.example.EmployeeManagementSystem.entity.Project;
import com.example.EmployeeManagementSystem.service.ProjectService;
import com.example.EmployeeManagementSystem.serviceImpli.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpli implements ProjectService {

    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public ProjectDTO createProject(ProjectDTO dto) {
        Project project = projectMapper.dtoToEntity(dto);
        return projectMapper.entityToDto(projectRepo.save(project));
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepo.findAll().stream().map(projectMapper::entityToDto).toList();
    }

    @Override
    public ProjectDTO getProjectById(Long id) {
        return projectMapper.entityToDto(projectRepo.findById(id).orElseThrow(()-> new RuntimeException("Project Not Found With ID:"+id)));
    }

    @Override
    public ProjectDTO updateProject(Long id, ProjectDTO dto) {
        Project project = projectRepo.findById(id).orElseThrow();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setStatus(dto.getStatus());
        project.setProjectStartDate(dto.getProjectStartDate());
        project.setProjectEndDate(dto.getProjectEndDate());
        return projectMapper.entityToDto(projectRepo.save(project));
    }

    @Override
    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }
}
