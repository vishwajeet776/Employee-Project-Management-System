package com.example.EmployeeManagementSystem.exceptionHandler;

public class Notes_Exception {

    /*

    create ExceptionResponse class to send error path, Date ,time , StatusCOde when error occur

    1 = we can define our own method in Repository and use in ServiceImpli class
    2 = Exception Handler class write " Custom Exception " OR  "Global Exceptions "
    3 = in Serviceimpli class we throw new CatageoryalreadyExist { created Exception class name }  but return larger unused messgae. thats why
    4 = in Controller class we use try { } catch( ) block to send userfriendly message user from Controller.


.......................

Global Exception

1 = create customException 1st "@ResponseStatus(HttpStatus.NOT_FOUND) use annotation in class ->
                use this class name in  Global Exception class inside @ExceptionHandler(classname.class )
                   then dont need to use try catch block
     */
}
