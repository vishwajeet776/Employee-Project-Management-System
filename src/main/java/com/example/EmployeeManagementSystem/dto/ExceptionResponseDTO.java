package com.example.EmployeeManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExceptionResponseDTO {   /*this class use for GlobalException Handling to show " error path, message, Date,Time .
                                                    in" GlobalExce. class use WebRequest " to access &
                                                           getDistricption( false )      */
    private String apiPath;
    private HttpStatus statusCode;
    private String errorMessage;
    private LocalDateTime errorTime;
}
