package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.TaskDTO;
import com.example.EmployeeManagementSystem.entity.Task;
import com.example.EmployeeManagementSystem.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary="Creating New Task", description="saving new task details and assign to employee" ,
            responses = { @ApiResponse(responseCode = "200" , description = "Task Cresate Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class ))),
                    @ApiResponse(responseCode = "404" , description = "Task Failed to Create")
            })
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
    @Operation(
            summary="Task Details By ID",
            description="Using ID method provide Task Details "
    )
    @ApiResponse(responseCode = "200" , description = "Task fetched successfully by id")
    public ResponseEntity<TaskDTO> getTask(@RequestParam Long id) {

        log.info("getTask - Fetching task with ID: {}", id);
        return ResponseEntity.ok(taskService.getTaskById(id));
    }


    @GetMapping("/list")
    @Operation(
            summary="List of Task", description="Fetching List of Task "
    )
    @ApiResponse( responseCode = "200" , description = "task list fetch successfully")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {

        log.info("getAllTasks - Retrieving all tasks");
        return ResponseEntity.ok(taskService.getAllTasks());
    }


    @PutMapping("/update")
    @Operation(
            summary="Updateing Task Details",
            description="Using ID method Update All required details "
    )
    public ResponseEntity<TaskDTO> updateTask(@RequestParam Long id, @RequestBody TaskDTO dto) {

        log.info("updateTask - Updating task with ID: {}", id);
       TaskDTO taskUpdate = taskService.updateTask(id, dto);

        log.info("updateTask - Task updated successfully with ID: {}", id);
        return ResponseEntity.status(200).body(taskUpdate);
    }


    @DeleteMapping("/delete")
    @Operation(
            summary="Deleteing Task ",
            description="Using ID deleting Task "
    )
    @ApiResponse(responseCode = "200" , description = "task delete successfully")
    public ResponseEntity <String> deleteTask(@RequestParam Long id) {

        log.info("deleteTask - Deleting task with ID: {}", id);
      return ResponseEntity.ok( taskService.deleteTask(id)  );

    }
}