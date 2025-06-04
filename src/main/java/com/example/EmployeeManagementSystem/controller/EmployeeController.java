package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /*
     @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDTO dto) {

            //400 Bad Request: Example for validation failure
            if (dto.getName() == null && dto.getSalary() == null)         // Name & Salary if Compusary to pass both field otherwise send error message.
            {
                return ResponseEntity.badRequest().body(" Name & salary is Not Null ");
            }
            employeeService.createEmployee(dto);
           return ResponseEntity.status(201).body("Employee Successfully Created.");     // without try{} catch block

    }

     */

    @PostMapping("/create")
    public ResponseEntity< ? > createEmployee(@RequestBody EmployeeDTO dto)      // " ? " use in place "EmployeeDTO " Generic type " it accept all type
    {
                                                                            //using try, catch we send " single line "error message to user insted multiple line msg
         logger.info("Post/create- creating employee: {}", dto.getName());                                                              //400 Bad Request: Example for validation failure
            if (dto.getName() == null && dto.getSalary() == null)         // Name & Salary if Compusary to pass both field otherwise send error message.
            {
                logger.warn("Post / Create - Missing name And salary");
                return ResponseEntity.badRequest().body(" Name & salary must Not Null ");
            }

                EmployeeDTO employeeDTO =    employeeService.createEmployee(dto);

              logger.info("Employee Created with ID: {} " , employeeDTO.getId() );
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeDTO);
    }


    @GetMapping("/get")
    public ResponseEntity<EmployeeDTO> getEmployee(@RequestParam Long id) {

        logger.debug("GET /get - Fetching employee using ID: {}", id);
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {

        logger.info("GET /list - Fetching all employees");
        List<EmployeeDTO> employees = employeeService.getAllEmployees();

        logger.info("Total Employee Foiund : {}", employees.size() );
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestParam Long id, @RequestBody EmployeeDTO dto) {

        logger.info("PUT /update - Updating employee with ID: {}", id);
        return ResponseEntity.ok(employeeService.updateEmployee(id, dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity< String > deleteEmployee(@RequestParam Long id) {

        logger.info("DELETE /delete - Deleting employee with ID: {}", id);
        employeeService.deleteEmployee(id);

        logger.info("Employee deleted with ID: {}", id);
        return ResponseEntity.noContent().build();   // 204 No Content
    }
}