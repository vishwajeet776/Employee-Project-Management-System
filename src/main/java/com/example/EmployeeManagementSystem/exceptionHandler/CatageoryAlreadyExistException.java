package com.example.EmployeeManagementSystem.exceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CatageoryAlreadyExistException extends RuntimeException {  /*Custom Exception Handle , use for Create method  in Employee Class
                  And used in GLobal Exception Class also to avoid try{} , catch( ) block in controller class .   */


    public CatageoryAlreadyExistException(String message)
    {

        super(message);

    }
}
