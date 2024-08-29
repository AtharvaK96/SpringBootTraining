package com.example.springDataJPA.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BookNotFoundException.class)
    public ResponseEntity<ValidationErrorObj> handleEmployeeNotFoundException(BookNotFoundException ex){
        ValidationErrorObj errorObj = new ValidationErrorObj();
        Map<String,String> errors = new HashMap<>();
        errors.put("message",ex.getMessage());
        errorObj.setError(errors);
        return new ResponseEntity<ValidationErrorObj>(errorObj,HttpStatus.NOT_FOUND) ;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)

   ResponseEntity<ValidationErrorObj>  onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String,String> errors = new HashMap<>();
        ValidationErrorObj errorObj = new ValidationErrorObj();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        errorObj.setError(errors);
        return new ResponseEntity<ValidationErrorObj>(errorObj,HttpStatus.BAD_REQUEST);
    }
}
