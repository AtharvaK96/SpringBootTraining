package com.Assignment_Two.assignment_two.exception;

public class DuplicateEmployeeIdException extends RuntimeException{

    public DuplicateEmployeeIdException(String message) {
        super(message);
    }


    public DuplicateEmployeeIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
