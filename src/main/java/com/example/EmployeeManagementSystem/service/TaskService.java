package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    public TaskDTO createTask(TaskDTO dto);

    public List<TaskDTO> getAllTasks();

    public TaskDTO getTaskById(Long id);

    public TaskDTO updateTask(Long id, TaskDTO dto);

    public void deleteTask(Long id);
}
