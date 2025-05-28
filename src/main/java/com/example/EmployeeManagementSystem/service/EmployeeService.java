package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    public EmployeeDTO createEmployee(EmployeeDTO dto);

    public List<EmployeeDTO> getAllEmployees();

    public EmployeeDTO getEmployeeById(Long id);

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto);

    public void deleteEmployee(Long id);
}
