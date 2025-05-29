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
    public ProjectDTO updateProject(Long id, ProjectDTO pdto) {

        Project existingProject = projectRepo.findById(id).orElseThrow(()->new RuntimeException("project not Found ID :"+ id));

       //ProjectService Check for ifNonNull Method howni have use..

        /*                     if(pdto.getName()!=null) {
                                    existingProject.setName(pdto.getName());   }   */

        ifNonNull(pdto.getName() , existingProject ::setName);
        ifNonNull(pdto.getDescription() ,  existingProject ::setDescription);
        ifNonNull(pdto.getStatus() , existingProject :: setStatus);
        ifNonNull(pdto.getProjectStartDate() , existingProject :: setProjectStartDate);
        ifNonNull(pdto.getProjectEndDate() , existingProject :: setProjectEndDate);

        return projectMapper.entityToDto(projectRepo.save(existingProject));

    }


    @Override
    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }
}
