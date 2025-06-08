package com.example.EmployeeManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Schema(                            //swagger changing class name EmployeeDTO to Employee last of swagger page
        name="Employee",
        description="it hold Employee details with clientId, projectid, addressId, taskId "
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String role;
    private Double salary;
    private LocalDate joiningDate;

    private AddressDTO addressDto;   /*addressDto when we use DtO object pass all address data from employee table and Address table store address data &
                                    " employee table store address id " */

    private Long clientId;    // here we pass only ID of client table data already available & need to create seperately   private Long projectId;
    private Long projectId;
    private Long taskId;
}