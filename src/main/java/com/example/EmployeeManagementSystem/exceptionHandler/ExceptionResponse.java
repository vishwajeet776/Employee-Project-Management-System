package com.example.EmployeeManagementSystem.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {   /*this class use for GlobalException Handling to show " error path, message, Date,Time .
                                                    in" GlobalExce. class use WebRequest " to access &
                                                           getDistricption( false )      */
    private String apiPath;
    private HttpStatus statusCode;
    private String errorMessage;
    private LocalDateTime timeStamp;
}

