package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.ProjectDTO;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.entity.Project;
import com.example.EmployeeManagementSystem.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@Slf4j
@Tag(name="Project API")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @PostMapping("/create")
    @Operation(  summary="creating new Project", description="by providing details method create new Project ",
            responses = {
                    @ApiResponse( responseCode = "201" , description = "CREATED" , content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Project.class))),
                    @ApiResponse(responseCode = "404", description = "project not CREATED")
            } )
    public ResponseEntity<String> createProject(@RequestBody ProjectDTO dto) {

        log.info("createProject- checking Name{}",dto.getName(), " status Not Empty{} ",dto.getStatus());
        if(dto.getName() == null || dto.getStatus() == null)
        {
            log.warn("createproject - Check Name & Status is Empty");
            return ResponseEntity.badRequest().body("provide Name & status field ");
        }

        projectService.createProject(dto);
        log.info("createProject - Project created successfully: {}", dto.getName());
        return ResponseEntity.status(201).body("project add successfully");
    }

    @GetMapping("/get")
    @Operation( summary = "Get employee by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found project",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "404", description = "Project not found") })
    public ResponseEntity<ProjectDTO> getProject(@RequestParam Long id) {

        log.info("getProject - Fetching project with ID: {}", id);
        return ResponseEntity.ok(projectService.getProjectById(id));
    }


    @GetMapping("/list")
    @Operation(
            summary="List project ", description="List of all project "
    )
    @ApiResponse(responseCode = "200" , description = "succesfully All Project Fetched")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {

        log.info("getAllProjects - Retrieving list of all projects");
        return ResponseEntity.ok(projectService.getAllProjects());
    }



    @PutMapping("/update")
    @Operation(
            summary="update project By id", description="by providing id & details method update all details about project "
    )
    @ApiResponse(responseCode = "200" , description = "project update successfully")
    public ResponseEntity<ProjectDTO> updateProject(@RequestParam Long id, @RequestBody ProjectDTO dto) {

        log.info("updateProject - Updating project with ID: {}", id);
        return ResponseEntity.ok(projectService.updateProject(id, dto));
    }



    @DeleteMapping("/delete")
    @Operation(
            summary="Delete project By id", description="Delete project By providing ID ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successfully project delete",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Project.class))),
                    @ApiResponse(responseCode = "404", description = "Project not found") })
    public ResponseEntity<String> deleteProject(@RequestParam Long id) {

        log.info("deleteProject - Deleting project with ID: {}", id);
        projectService.deleteProject(id);

        log.info("deleteProject - Project deleted successfully with ID: {}", id);
        return ResponseEntity.ok("Project deleted successfully");
    }
}
