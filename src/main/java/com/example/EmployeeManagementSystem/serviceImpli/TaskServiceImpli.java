package com.example.EmployeeManagementSystem.serviceImpli;

import com.example.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.Repository.ProjectRepository;
import com.example.EmployeeManagementSystem.Repository.TaskRepository;
import com.example.EmployeeManagementSystem.dto.TaskDTO;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.entity.Project;
import com.example.EmployeeManagementSystem.entity.Task;
import com.example.EmployeeManagementSystem.exceptionHandler.ResourseNotFoundException;
import com.example.EmployeeManagementSystem.mapper.TaskMapper;
import com.example.EmployeeManagementSystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpli implements TaskService {


    private  final TaskRepository taskRepo;

    private final EmployeeRepository employeeRepo;
    private final ProjectRepository projectRepo;
    private final TaskMapper  taskMapper;

    @Override
    public TaskDTO createTask(TaskDTO dto) {

        log.info("Creating new task: {}", dto.getDescription());
        Task task = taskMapper.dtoToEntity(dto);

        Task t = taskRepo.save(task);

        log.info("Task created with ID: {}", t.getId());
        return taskMapper.entityToDto(t);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        log.info("Fetching all tasks list");
        return taskRepo.findAll().stream().map(taskMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        log.info("Fetching task by ID: {}", id);
    return taskMapper.entityToDto(taskRepo.findById(id).orElseThrow(()->new  ResourseNotFoundException("! task not found With ID: "+ id)));
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO dto) {
        log.info("Updating task with ID: {}", id);
        Task existingTask = taskRepo.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("! Task not found with id: " + id));

        // all "if{} condition check any field not provide ot updtate then store privious value automatically

        if(dto.getDescription()!=null) {
            existingTask.setDescription(dto.getDescription());
        }
        if(dto.getStatus()!=null) {
            existingTask.setStatus(dto.getStatus());
        }
        if(dto.getEndDate() !=null) {
            existingTask.setEndDate(dto.getEndDate());
        }

        if(dto.getEmployeeId()!=null)       // Update employees if list is provided
        {
          List<Employee> empAvailable = employeeRepo.findAllById(dto.getEmployeeId());

            existingTask.setEmployee(empAvailable);

        }

        if(dto.getProjectId()!=null) {
          Project project=  projectRepo.findById(dto.getProjectId()).orElseThrow(() ->
                    new ResourseNotFoundException("! Project not found with ID :" + dto.getProjectId()));

          existingTask.setProject(project);
        }


        Task updatedTask = taskRepo.save(existingTask);
        log.info("Task updated successfully with ID: {}", updatedTask.getId());
        return taskMapper.entityToDto(updatedTask);
    }

    @Override
    public String deleteTask(Long id) {
        log.info("Deleting task with ID: {}", id);
     Task  taskDelete = taskRepo.findById(id).orElseThrow(()-> new ResourseNotFoundException("! Id not found: "+id));

        // ResourseNotFoundException declare in exceptionHandler package
           taskRepo.delete(taskDelete);
             log.info("Task deleted successfully with ID: {}", id);
               return "task deleted with ID: "+ id;
    }
}
