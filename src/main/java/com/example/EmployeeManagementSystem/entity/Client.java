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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;

    @ManyToOne(cascade = CascadeType.ALL)          // delete client then Address also Deleted bez " CascadeType.All "
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "client")
    private List<Employee> employees;

    @OneToMany(mappedBy = "client")
    private List<Project> projects;
}

/*   postman ( http://localhost:8080/api/client/get?id=1 )
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "123-456-7890",
  "position": "Developer",
  "addressDto": {
    "address": "456 Oak Avenue",
    "city": "Springfield",
    "state": "IL",
    "pincode": "62704"
  }
}


 */