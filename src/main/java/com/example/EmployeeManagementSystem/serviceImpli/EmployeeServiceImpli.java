package com.example.EmployeeManagementSystem.serviceImpli;

import com.example.EmployeeManagementSystem.Repository.ClientRepository;
import com.example.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.Repository.TaskRepository;
import com.example.EmployeeManagementSystem.dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.entity.Client;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.entity.Task;
import com.example.EmployeeManagementSystem.exceptionHandler.ResourceAlreadyExistsException;
import com.example.EmployeeManagementSystem.exceptionHandler.ResourseNotFoundException;
import com.example.EmployeeManagementSystem.mapper.EmployeeMapper;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpli implements EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private ClientRepository clientRepo;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpli.class);


    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {

        logger.info("Checking Name in Database");
        Optional<Employee> empNameFind = empRepo.findByName(dto.getName());
        if (empNameFind.isPresent()) {

            logger.error("Employee already Exist Name: {} ", dto.getName());
            throw new ResourceAlreadyExistsException("Employee Name = " + dto.getName() + " Already Exist !");
            //   throw  new RuntimeException("Employee Name Already Exist");  // before implimenting  CustomException
        }

        logger.info("Save new Employee And convert DtoToEntity");
        Employee employee = employeeMapper.dtoToEntity(dto);
        return employeeMapper.entityToDto(empRepo.save(employee));
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        logger.info("Fetching All Data");
        return empRepo.findAll().stream().map(employeeMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {

        logger.info("Fetching employee by ID: {}", id);
        return employeeMapper.entityToDto(empRepo.findById(id).orElseThrow(
                () -> new ResourseNotFoundException("! employee not Found Id: " + id)));
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {

        logger.info("service = checking id  to update in database {}", id);
        Employee emp = empRepo.findById(id).
                orElseThrow(() -> {
                    logger.error("employee ID not Found ID: {}", id);
              return  new ResourseNotFoundException("! Employee not found ID: " + id);
                });

        // Do NOT change description, startDate, endDate,email or projectId (they remain as it is) . in " if( ) block  " those value set want to update other field unchange
         /* task_id is " FK column" task_id change assign from task class . from task class assign task to employee & task_id auto store in
                 in employee table
          */
        if (dto.getPhone() != null) {

            logger.info("Updating phone for employee ID: {}", id);
            emp.setPhone(dto.getPhone());
        }
        if (dto.getSalary() != null) {
            logger.info("Updating salary for employee ID: {}", id);
            emp.setSalary(dto.getSalary());
        }

        //clientId is Fk and we update them.
        if (dto.getClientId() != null) {
            logger.info("Finding Employee in service ID {}", dto.getClientId());
            Client client = clientRepo.findById(dto.getClientId()).orElseThrow(() ->
                    new ResourseNotFoundException("! client not Found ID: " + dto.getClientId()));
            logger.info("Set Client ID in employee");
            emp.setClient(client);
        }

        if (dto.getTaskId() != null) {

            logger.info("Find task ID to set: {}", id);
            Task taskAvailable = taskRepo.findById(dto.getTaskId()).
                    orElseThrow(() ->
                        new ResourseNotFoundException("! task not Found: " + dto.getTaskId()));

            logger.info("setting ID in emp {}", dto.getTaskId());
            emp.setTask(taskAvailable);

        }
        logger.info("updating All data Employee ");
        return employeeMapper.entityToDto(empRepo.save(emp));

    }

    @Override
    public void deleteEmployee(Long id) {

        logger.info(" delete employee with ID: {}", id);

        empRepo.findById(id).orElseThrow(() -> {
            logger.error("Employee not found for deletion with ID: {}", id);
            return new ResourseNotFoundException("! Employee Not Found ID: " + id);
        });

        empRepo.deleteById(id);
        logger.info("Employee deleted successfully with ID: {}", id);
    }
}
