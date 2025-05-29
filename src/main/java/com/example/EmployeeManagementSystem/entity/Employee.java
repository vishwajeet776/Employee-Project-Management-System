package com.example.EmployeeManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String role;
    private Double salary;
    private LocalDate joiningDate;

    @OneToOne(cascade = CascadeType.PERSIST)       //PERSIST = When you save() the parent, the related entity is also saved. Use when creating new related entities together.// CascadeType.All =Applies all the above operations.	Use only if the child fully depends on the parent and should mirror changes.
    @JoinColumn(name = "address_id" )
    @JsonBackReference
    private Address address;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonBackReference
    private Project project;

    @ManyToOne(cascade = CascadeType.ALL)    //owns the FK column "task_id" in employee table task id assign when data send from task provide employeeID
    @JoinColumn(name = "task_id")
    @JsonBackReference
    private Task task;
}

/*
{
  "name": "Alice Smith",
  "email": "alice.smith@example.com",
  "phone": "9876543210",
  "role": "Frontend Developer",
  "salary": 75000.0,
  "joiningDate": "2024-08-15",
  "clientId": 1,
  "projectId": 2,
  "addressDto": {
    "city": "San Francisco",
    "state": "CA",
    "pincode": "94103"
  }
}


 */