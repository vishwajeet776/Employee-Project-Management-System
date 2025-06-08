package com.example.EmployeeManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Schema(
        name="Project",
        description="it hold Project details 7 clientid"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private String status;
    private LocalDate projectStartDate;
    private LocalDate projectEndDate;
    private Long clientId;

    private List<Long> employeeIds;     // Employee IDs list
    private List<Long> taskIds;         // Task IDs list



}