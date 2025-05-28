package com.example.EmployeeManagementSystem.serviceImpli;

import com.example.EmployeeManagementSystem.Repository.TaskRepository;
import com.example.EmployeeManagementSystem.dto.TaskDTO;
import com.example.EmployeeManagementSystem.entity.Task;
import com.example.EmployeeManagementSystem.service.TaskService;
import com.example.EmployeeManagementSystem.serviceImpli.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpli implements TaskService {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private TaskMapper taskMapper;

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

        existingTask.setTitle(dto.getTitle());
        existingTask.setDescription(dto.getDescription());
        existingTask.setStatus(dto.getStatus());
        existingTask.setStartDate(dto.getStartDate());
        existingTask.setEndDate(dto.getEndDate());

        // You may choose to update employee/project here too, if needed
        Task updatedTask = taskRepo.save(existingTask);
        return taskMapper.entityToDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
}
