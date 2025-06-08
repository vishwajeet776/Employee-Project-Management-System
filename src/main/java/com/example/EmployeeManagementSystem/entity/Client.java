package com.example.EmployeeManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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

    @NotBlank(message="name cannot blank")
    @Size(min = 2 ,max=30 , message = "name character length between 2 to 30")
    private String name;

    @NotBlank(message = "email is Required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Phone No. Required")
    private String phone;

    @NotNull(message = "Contract start date is required")
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