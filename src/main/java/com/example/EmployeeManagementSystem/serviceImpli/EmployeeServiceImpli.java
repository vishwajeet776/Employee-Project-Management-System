package com.example.EmployeeManagementSystem.serviceImpli;

import com.example.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import com.example.EmployeeManagementSystem.serviceImpli.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpli implements EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee employee = employeeMapper.dtoToEntity(dto);
        return employeeMapper.entityToDto(empRepo.save(employee));
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return empRepo.findAll().stream().map(employeeMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        return employeeMapper.entityToDto(empRepo.findById(id).orElseThrow());
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee emp = empRepo.findById(id).orElseThrow(()->new RuntimeException("Employee not found ID: "+id));
        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
       
        return employeeMapper.entityToDto(empRepo.save(emp));
    }

    @Override
    public void deleteEmployee(Long id) {
        empRepo.deleteById(id);
    }
}
