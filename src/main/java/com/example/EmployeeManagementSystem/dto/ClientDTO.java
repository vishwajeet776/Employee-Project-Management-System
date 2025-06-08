package com.example.EmployeeManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Schema(                            //swagger changing class name ClientDTO to Client last of swagger page
        name="Client",
        description="it hold Client details & Address id"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private AddressDTO addressDto;

   // private  AddressDTO addressDto;
}