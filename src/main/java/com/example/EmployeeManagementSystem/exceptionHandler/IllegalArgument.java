package com.example.EmployeeManagementSystem.exceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

public class IllegalArgument extends RuntimeException{

    public   IllegalArgument (String message)
    {
        super(message);
    }
}

