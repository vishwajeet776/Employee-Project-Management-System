package com.example.EmployeeManagementSystem.mapper;

import com.example.EmployeeManagementSystem.Repository.ClientRepository;
import com.example.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.Repository.ProjectRepository;
import com.example.EmployeeManagementSystem.Repository.TaskRepository;
import com.example.EmployeeManagementSystem.dto.AddressDTO;
import com.example.EmployeeManagementSystem.dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.dto.TaskDTO;
import com.example.EmployeeManagementSystem.entity.Address;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.entity.Project;
import com.example.EmployeeManagementSystem.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class TaskMapper {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private ProjectRepository projectRepo;

    public TaskDTO entityToDto(Task task) {
        TaskDTO dto = new TaskDTO();

        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setStartDate(task.getStartDate());
        dto.setEndDate(task.getEndDate());

        // Correctly map List<Employee> to List<Long> employee IDs
        if (task.getEmployee() != null ) {
            List<Long> employeeIds = task.getEmployee().stream()
                    .map(Employee::getId)
                    .collect(Collectors.toList());

            dto.setEmployeeId(employeeIds);  // Make sure TaskDTO has List<Long> employeeId field
        }

        if (task.getProject() != null) {
            dto.setProjectId(task.getProject().getId());
        }

        return dto;
    }


    public Task dtoToEntity(TaskDTO dto) {
        Task task = new Task();

        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setStartDate(dto.getStartDate());
        task.setEndDate(dto.getEndDate());

        //this is use to check emp ID avilable or not and set taskId in employee table

        if (dto.getEmployeeId() != null) {           //// assuming each employee links to this task
            List<Employee> employees = dto.getEmployeeId().stream()
                    .map(id -> employeeRepo.findById(id)
                            .orElseThrow(() -> new RuntimeException("Employee not found: " + id)))
                    .collect(Collectors.toList());

            for(Employee emp : employees){
                emp.setTask(task);
            }

            task.setEmployee(employees);

        }

        if (dto.getProjectId() != null) {
            Project project = projectRepo.findById(dto.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found: " + dto.getProjectId()));


            task.setProject(project);
        }

        return task;
    }



}