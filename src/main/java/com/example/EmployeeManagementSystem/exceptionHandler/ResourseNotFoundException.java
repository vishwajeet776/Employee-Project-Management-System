package com.example.EmployeeManagementSystem.exceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(HttpStatus.NOT_FOUND)     //// Automatically sends 404 response , Custom Exception use for All " Delete " method in Service class.
public class ResourseNotFoundException extends RuntimeException{

    public ResourseNotFoundException (String message)
    {
        super(message);
    }





}
