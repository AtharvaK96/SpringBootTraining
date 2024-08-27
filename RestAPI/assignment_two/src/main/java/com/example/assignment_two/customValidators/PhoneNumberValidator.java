package com.example.assignment_two.customValidators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        String phoneNoRegEx = "^(\\+91[-\\s]?)?[789]\\d{9}$";
       return Pattern.matches(phoneNoRegEx,value);
    }
}
