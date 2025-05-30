package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.ProjectDTO;
import com.example.EmployeeManagementSystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<String> createProject(@RequestBody ProjectDTO dto) {

        if(dto.getName() == null || dto.getStatus() == null)
        {
            return ResponseEntity.badRequest().body("provide Name & status field ");
        }
        projectService.createProject(dto);

        return ResponseEntity.status(201).body("project add successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<ProjectDTO> getProject(@RequestParam Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @PutMapping("/update")
    public ResponseEntity<ProjectDTO> updateProject(@RequestParam Long id, @RequestBody ProjectDTO dto) {
        return ResponseEntity.ok(projectService.updateProject(id, dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProject(@RequestParam Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Project deleted successfully");
    }
}
