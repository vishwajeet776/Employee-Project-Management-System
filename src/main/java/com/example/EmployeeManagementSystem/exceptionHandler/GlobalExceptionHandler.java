package com.example.EmployeeManagementSystem.exceptionHandler;


import com.example.EmployeeManagementSystem.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalExceptionHandler {                     // dont need to use try catch block for ex. use in " EmployeServiceImpli clas in create method & controller method "


@ExceptionHandler(CatageoryAlreadyExistException.class)     //customException class name i Annotation call custom Exption class
public ResponseEntity<String> handleCatageoryAlreadyExistException (CatageoryAlreadyExistException ex)
{
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
}


@ExceptionHandler(ResourseNotFoundException.class)               //this line check Exception in 1st & 2nd method if class match with Exception the throw otherwise go to last method Exception.class.
public ResponseEntity<ExceptionResponseDTO> handleResourseNotFoundException(ResourseNotFoundException ex, WebRequest webRequest)
{
    //workable only where ResourseNotFoundException Declare

    ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(      //use= status, error msg, Date & Time

            webRequest.getDescription(false),

            HttpStatus.NOT_FOUND,
            ex.getMessage(),
            LocalDateTime.now()
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponseDTO);
}

@ExceptionHandler(Exception.class)                           // if no method match then this Exception.class throw Exception
public ResponseEntity<ExceptionResponseDTO> handleGlobalException(Exception ex, WebRequest webRequest) {

    ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(      //use= status, error msg, Date & Time

            webRequest.getDescription(false),

            HttpStatus.NOT_FOUND,
            ex.getMessage(),
            LocalDateTime.now()
    );
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponseDTO);
}

}
