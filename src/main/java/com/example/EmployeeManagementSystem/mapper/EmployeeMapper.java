package com.example.EmployeeManagementSystem.mapper;

import com.example.EmployeeManagementSystem.Repository.*;
import com.example.EmployeeManagementSystem.dto.AddressDTO;
import com.example.EmployeeManagementSystem.dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    @Autowired
    private AddressRepository addressRepo;
    @Autowired
    private ClientRepository clientRepo;
    @Autowired
    private ProjectRepository projectRepo;
    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private EmployeeRepository employeeRepo ;

    public EmployeeDTO entityToDto(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();

        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setRole(employee.getRole());
        dto.setSalary(employee.getSalary());
        dto.setJoiningDate(employee.getJoiningDate());

        // Address Mapping
        if (employee.getAddress() != null) {
            AddressDTO addressDto = new AddressDTO();
            addressDto.setAddress(employee.getAddress().getAddress());
            addressDto.setCity(employee.getAddress().getCity());
            addressDto.setState(employee.getAddress().getState());
            addressDto.setPincode(employee.getAddress().getPincode());
            dto.setAddressDto(addressDto);
        }

        // Client & Project IDs
        if (employee.getClient() != null) {
            dto.setClientId(employee.getClient().getId());
        }
        if (employee.getProject() != null) {
            dto.setProjectId(employee.getProject().getId());

        }

        if(employee.getTask()!=null) {
            dto.setTaskId(employee.getTask().getId());
        }
        return dto;
    }

    public Employee dtoToEntity(EmployeeDTO dto) {
        Employee employee = new Employee();

        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setRole(dto.getRole());
        employee.setSalary(dto.getSalary());
        employee.setJoiningDate(dto.getJoiningDate());

        // Address Mapping
        if (dto.getAddressDto() != null) {
            Address address = new Address();

            address.setAddress  (dto.getAddressDto().getAddress() );
            address.setCity(dto.getAddressDto().getCity());
            address.setState(dto.getAddressDto().getState());
            address.setPincode(dto.getAddressDto().getPincode());
            employee.setAddress(address);
        }

        // Client & Project Mapping
        if (dto.getClientId() != null) {
            Client client = clientRepo.findById(dto.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));
            employee.setClient(client);
        }

        if (dto.getProjectId() != null) {
            Project project = projectRepo.findById(dto.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
            employee.setProject(project);
        }

        if(dto.getTaskId()!= null) {
            Task task = taskRepo.findById(dto.getTaskId()).orElseThrow(() -> new RuntimeException("task not found with ID :" + dto.getTaskId()));
        }
        return employee;
    }
}
