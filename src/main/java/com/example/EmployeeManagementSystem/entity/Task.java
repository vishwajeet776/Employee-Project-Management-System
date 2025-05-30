package com.example.EmployeeManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private String status;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "task" )     //cascade = CascadeType.ALL  := use ten after delete task id then employee also delet bez taskId assign with employee
    @JsonManagedReference
    private List <Employee> employee;

    @ManyToOne
 //   @JoinColumn(name = "project_id")  FK column created
    private Project project;


}

/*

  {
  "title": "API Testing",
  "description": "Test product issues",
  "status": "Pending",
  "startDate": "2024-05-01",
  "endDate": "2024-06-01",
  "employeeIds": [1, 2, 3],
  "projectId": 2
}

 */