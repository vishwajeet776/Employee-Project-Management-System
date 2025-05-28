package com.example.EmployeeManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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