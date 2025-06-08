package com.example.EmployeeManagementSystem.dto;

import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.entity.Task;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Schema(                        //swagger changing class name " TaskDTO to Task " of swagger page
        name="Task",
        description = "it hold task details & projectId and employeeId to assign task store taskId in employee table"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;

    private List<Long> employeeId;
    private Long projectId;

}