package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.TaskDTO;
import com.example.EmployeeManagementSystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody TaskDTO dto) {

        if(dto.getTitle() ==null || dto.getTitle().isEmpty() || dto.getStatus()==null )
        {
            return ResponseEntity.badRequest().body("provide Title & Status properly ");
        }

        taskService.createTask(dto);

        return ResponseEntity.status(201).body( "task created ");
    }

    @GetMapping("/get")
    public ResponseEntity<TaskDTO> getTask(@RequestParam Long id) {

        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {

        return ResponseEntity.ok(taskService.getAllTasks());
    }


    @PutMapping("/update")
    public ResponseEntity<TaskDTO> updateTask(@RequestParam Long id, @RequestBody TaskDTO dto) {

       TaskDTO taskUpdate = taskService.updateTask(id, dto);

        return ResponseEntity.status(200).body(taskUpdate);
    }


    @DeleteMapping("/delete")
    public ResponseEntity <String> deleteTask(@RequestParam Long id) {

      return ResponseEntity.ok( taskService.deleteTask(id)  );

    }
}