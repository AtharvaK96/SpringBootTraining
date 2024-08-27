package com.Assignment_Two.assignment_two.exception;

public class DuplicateEmployeeIdException extends RuntimeException{
    // Constructor that accepts a message
    public DuplicateEmployeeIdException(String message) {
        super(message);
    }

    // Constructor that accepts a message and a cause
    public DuplicateEmployeeIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
