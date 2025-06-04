package com.example.EmployeeManagementSystem.exceptionHandler;


// @ResponseStatus(value = HttpStatus.CONFLICT)                  // annotation not requre bez we use global Exce. and with method we use @Exceptionhandler annotation this anno. work & handle
public class ResourceAlreadyExistsException extends RuntimeException {  /*Custom Exception Handle , use for Create method  in Employee Class  */

    public ResourceAlreadyExistsException(String message)
    {
        super(message);
    }
}

//And used in GLobal Exception Class also to avoid try{} , catch( ) block in controller class .