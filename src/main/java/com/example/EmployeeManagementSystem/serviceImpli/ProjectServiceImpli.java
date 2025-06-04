package com.example.EmployeeManagementSystem.serviceImpli;

import com.example.EmployeeManagementSystem.Repository.ClientRepository;
import com.example.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.Repository.ProjectRepository;
import com.example.EmployeeManagementSystem.Repository.TaskRepository;
import com.example.EmployeeManagementSystem.dto.ProjectDTO;
import com.example.EmployeeManagementSystem.entity.Client;
import com.example.EmployeeManagementSystem.entity.Project;
import com.example.EmployeeManagementSystem.entity.Task;
import com.example.EmployeeManagementSystem.exceptionHandler.ResourseNotFoundException;
import com.example.EmployeeManagementSystem.mapper.ProjectMapper;
import com.example.EmployeeManagementSystem.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProjectServiceImpli implements ProjectService {

    @Autowired
    private ProjectRepository projectRepo;
    @Autowired
    private ClientRepository clientRepo;
    @Autowired
    private ProjectMapper  projectMapper;

    @Override
    public ProjectDTO createProject(ProjectDTO dto) {

        log.info("createProject - Saving project: {}", dto);
        Project project = projectMapper.dtoToEntity(dto);

        log.info("createProject- project successfully created {}", dto.getId());
        return projectMapper.entityToDto(projectRepo.save(project));
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        log.info("getAllProjects - Fetching all projects");
        return projectRepo.findAll().stream().map(projectMapper::entityToDto).toList();
    }

    @Override
    public ProjectDTO getProjectById(Long id) {

        log.info("getProjectById - Fetching project with ID: {}", id);
        return projectMapper.entityToDto(projectRepo.findById(id).orElseThrow(()-> new ResourseNotFoundException("! Project Not Found With ID:"+id)));
    }

    @Override
    public ProjectDTO updateProject(Long id, ProjectDTO pdto) {

        log.info("updateProject - Updating project with ID: {}", id);
        Project existingProject = projectRepo.findById(id).orElseThrow(()->new ResourseNotFoundException("! project not Found ID :"+ id));

       //ProjectService Check for ifNonNull Method howni have use..

        /*                     if(pdto.getName()!=null) {
                                    existingProject.setName(pdto.getName());   }   */

        ifNonNull(pdto.getName() , existingProject ::setName);
        ifNonNull(pdto.getDescription() , existingProject ::setDescription);
        ifNonNull(pdto.getStatus() , existingProject :: setStatus);
        ifNonNull(pdto.getProjectStartDate() , existingProject :: setProjectStartDate);
        ifNonNull(pdto.getProjectEndDate() , existingProject :: setProjectEndDate);

        if(pdto.getClientId() != null) {

            existingProject.setClient( clientRepo.findById(pdto.getClientId()).orElseThrow(() -> new ResourseNotFoundException
                                                        ("! client Not found with ID :" + id)));

        }
           log.info("project Update Successfully");
            return projectMapper.entityToDto(projectRepo.save(existingProject));


        }


    @Override
    public void deleteProject(Long id) {

        log.info("deleteProject - Deleting project with ID: {}", id);
        projectRepo.findById(id).orElseThrow(()-> {
            log.error("deleteProject - Project not found with ID: {}", id);
            return new ResourseNotFoundException("! project Not Found ID:" + id);
        });

        projectRepo.deleteById(id);
        log.info("deleteProject - Project deleted with ID: {}", id);
    }
}
