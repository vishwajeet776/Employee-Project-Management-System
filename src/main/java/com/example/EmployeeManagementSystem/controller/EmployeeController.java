package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Tag(name="Emoployee API")                              //Swagger= name change employee-Controller TO Employee API use @tag
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
    @Operation(  summary = "Get employee by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee CREATED",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "404", description = "Employee not CREATED") }
    )
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
    @Operation( summary = "Get employee by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found employee", content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "404", description = "Employee not found") }
    )
    public ResponseEntity<EmployeeDTO> getEmployee(@RequestParam Long id) {

        logger.debug("GET /get - Fetching employee using ID: {}", id);
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }




    @GetMapping("/list")
    @Operation(summary="List of Employee",  description="this method gives All Employee List.",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Found Employee List", content=@Content(mediaType ="application/json" ,
                                    schema = @Schema(implementation = Employee.class ) )),
                    @ApiResponse(responseCode = "404", description = "Employee not Found") }
    )
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {

        logger.info("GET /list - Fetching all employees");
        List<EmployeeDTO> employees = employeeService.getAllEmployees();

        logger.info("Total Employee Foiund : {}", employees.size() );
        return ResponseEntity.ok(employees);
    }



    @PutMapping("/update")
    @Operation( summary="updating Employee", description="by providing id we can update details of Employee ",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "appication/json",
                            schema = @Schema(implementation = Employee.class) )),
                    @ApiResponse(responseCode = "404") }
    )
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestParam Long id, @RequestBody EmployeeDTO dto) {

        logger.info("PUT /update - Updating employee with ID: {}", id);
        return ResponseEntity.ok(employeeService.updateEmployee(id, dto));
    }


    @DeleteMapping("/delete")
    @Operation(
            summary="delete Employee", description="by providing id we can delete details of Employee ",
            responses = {
                    @ApiResponse(responseCode = "200" , description = "employee deleted successfully",content = @Content(mediaType = "appication/json",
                            schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "404" , description = "Employee Not Found") }
    )
    public ResponseEntity< String > deleteEmployee(@RequestParam Long id) {

        logger.info("DELETE /delete - Deleting employee with ID: {}", id);
        employeeService.deleteEmployee(id);

        logger.info("Employee deleted with ID: {}", id);
        return ResponseEntity.noContent().build();   // 204 No Content
    }
}