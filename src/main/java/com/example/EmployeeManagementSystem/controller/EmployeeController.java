package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.exceptionHandler.CatageoryAlreadyExistException;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

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
        try {                                                               //using try, catch we send " single line "error message to user insted multiple line msg
                                                                           //400 Bad Request: Example for validation failure
            if (dto.getName() == null && dto.getSalary() == null)         // Name & Salary if Compusary to pass both field otherwise send error message.
            {
                return ResponseEntity.badRequest().body(" Name & salary is Not Null ");
            }
        EmployeeDTO employeeDTO =    employeeService.createEmployee(dto);

            return ResponseEntity.status(HttpStatus.CREATED).body(employeeDTO);
        }
        catch (CatageoryAlreadyExistException ex)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( ex.getMessage() );
        }
    }


    @GetMapping("/get")
    public ResponseEntity<EmployeeDTO> getEmployee(@RequestParam Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestParam Long id, @RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity< String > deleteEmployee(@RequestParam Long id) {

        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();   // 204 No Content
    }
}