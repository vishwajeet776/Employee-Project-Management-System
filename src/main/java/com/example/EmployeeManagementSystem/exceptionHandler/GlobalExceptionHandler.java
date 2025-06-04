package com.example.EmployeeManagementSystem.exceptionHandler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgument.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgument ex)
    {
        log.warn("GlobalException-  llegalArgument Error occur", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST ) .body(ex.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)                  // use with EmployeeServiceImpli class for "create" method to avoid dublicate name .
    public ResponseEntity<ExceptionResponse> handleResourceAlreadyExists(ResourceAlreadyExistsException ex, WebRequest request) {

        log.warn("globalException- data AlreadyExist Error occur", ex.getMessage());
        ExceptionResponse response = new ExceptionResponse(
                request.getDescription(false),
                HttpStatus.CONFLICT,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }


    //this line check Exception in 1st & 2nd method if class match with Exception the throw otherwise go to last method Exception.class.

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourseNotFoundException(ResourseNotFoundException ex, WebRequest webRequest)
    {

        log.warn("GlobalExceptiion- ResourseNotFound Exception: {} ",ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(        //workable only where method throw ResourseNotFoundException Declare in m

                webRequest.getDescription(false),

                HttpStatus.NOT_FOUND,                              // this all taken from Exception class
                ex.getMessage(),
                LocalDateTime.now()
        );                                                                //use= status, error msg, Date & Time
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }


    @ExceptionHandler(Exception.class)
    // if no method match then this Exception.class throw Exception
    public ResponseEntity<ExceptionResponse> handleGlobalException(Exception ex, WebRequest webRequest) {

        log.error("Unhandled exception occurred: {}", ex.getMessage(), ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(              //use= status, error msg, Date & Time

                webRequest.getDescription(false),                 // path (e.g., "/api/employees/5")

                HttpStatus.INTERNAL_SERVER_ERROR,                              //  status code (e.g., 404)
                ex.getMessage(),                                          // exception message
                LocalDateTime.now()                                      // current timestamp
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }


}
