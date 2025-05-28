package com.example.EmployeeManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String status;
    private LocalDate projectStartDate;
    private LocalDate projectEndDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "project")
    private List<Employee> employees;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;
}

/*
{

    "name": "Microsoft",
    "desription": "product not showing ",
    "status": "pending",
    "projectStartDate":"2020-05-01",
    "projectEndDate": "2020-06-25",
    "clientId": 2
}
 */