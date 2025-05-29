package com.example.EmployeeManagementSystem.serviceImpli;

import com.example.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.Repository.ProjectRepository;
import com.example.EmployeeManagementSystem.Repository.TaskRepository;
import com.example.EmployeeManagementSystem.dto.TaskDTO;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.entity.Project;
import com.example.EmployeeManagementSystem.entity.Task;
import com.example.EmployeeManagementSystem.mapper.TaskMapper;
import com.example.EmployeeManagementSystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpli implements TaskService {


    private  final TaskRepository taskRepo;

    private final EmployeeRepository employeeRepo;
    private final ProjectRepository projectRepo;
    private final TaskMapper  taskMapper;

    @Override
    public TaskDTO createTask(TaskDTO dto) {
        Task task = taskMapper.dtoToEntity(dto);

        Task t = taskRepo.save(task);

        return taskMapper.entityToDto(t);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepo.findAll().stream().map(taskMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        return taskMapper.entityToDto(taskRepo.findById(id).orElseThrow(()->new RuntimeException("task not found With ID: "+ id)));
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO dto) {
        Task existingTask = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        // all "if{} condition check any field not provide ot updtate then store privious value automatically
        if(dto.getTitle()!=null) {
            existingTask.setTitle(dto.getTitle());
        }
        if(dto.getDescription()!=null) {
            existingTask.setDescription(dto.getDescription());
        }
        if(dto.getStatus()!=null) {
            existingTask.setStatus(dto.getStatus());
        }
        if(dto.getStartDate()!=null) {
            existingTask.setStartDate(dto.getStartDate());
        }
        if(dto.getEndDate() !=null) {
            existingTask.setEndDate(dto.getEndDate());
        }

        if(dto.getEmployeeId()!=null)       // Update employees if list is provided
        {
          List<Employee> empAvailable = employeeRepo.findAllById(dto.getEmployeeId());

          for(Employee emp : empAvailable)       // forEach loop use to set foreign key taskId in employee table
          {
              emp.setTask(existingTask);
          }
            existingTask.setEmployee(empAvailable);

        }

        if(dto.getProjectId()!=null) {
          Project project=  projectRepo.findById(dto.getProjectId()).orElseThrow(() ->
                    new RuntimeException("Project not found with ID :" + dto.getProjectId()));

          existingTask.setProject(project);
        }


        Task updatedTask = taskRepo.save(existingTask);
        return taskMapper.entityToDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
}
