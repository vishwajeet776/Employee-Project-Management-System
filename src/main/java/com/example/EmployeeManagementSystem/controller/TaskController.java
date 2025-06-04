package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.TaskDTO;
import com.example.EmployeeManagementSystem.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody TaskDTO dto) {

        log.info("createTask- Checking Title{} ",dto.getTitle(), "And Status:{}",dto.getStatus());
        if(dto.getTitle() ==null || dto.getTitle().isEmpty() || dto.getStatus()==null )
        {
            log.warn("createTask - Title And Status is empty ! ");
            return ResponseEntity.badRequest().body("provide Title & Status properly ");
        }

        taskService.createTask(dto);
        log.info("createTask - Task created successfully with title: {}", dto.getTitle());
        return ResponseEntity.status(201).body( "task created ");
    }

    @GetMapping("/get")
    public ResponseEntity<TaskDTO> getTask(@RequestParam Long id) {

        log.info("getTask - Fetching task with ID: {}", id);
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {

        log.info("getAllTasks - Retrieving all tasks");
        return ResponseEntity.ok(taskService.getAllTasks());
    }


    @PutMapping("/update")
    public ResponseEntity<TaskDTO> updateTask(@RequestParam Long id, @RequestBody TaskDTO dto) {

        log.info("updateTask - Updating task with ID: {}", id);
       TaskDTO taskUpdate = taskService.updateTask(id, dto);

        log.info("updateTask - Task updated successfully with ID: {}", id);
        return ResponseEntity.status(200).body(taskUpdate);
    }


    @DeleteMapping("/delete")
    public ResponseEntity <String> deleteTask(@RequestParam Long id) {

        log.info("deleteTask - Deleting task with ID: {}", id);
      return ResponseEntity.ok( taskService.deleteTask(id)  );

    }
}