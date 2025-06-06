package com.example.EmployeeManagementSystem.Repository;

import com.example.EmployeeManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

   Optional<  Employee > findByName(String empName);             //EmployeeService- create method - check name already exist then throw custom exception

}
