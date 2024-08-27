package com.Assignment_Two.assignment_two.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidatePassword implements ConstraintValidator<ValidateEmployeePassword, String> {

    // Password pattern to ensure it has at least one lowercase letter, one uppercase letter, one digit, one special character, and is at least 8 characters long
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%?&]{8,}$"
    );

    @Override
    public void initialize(ValidateEmployeePassword constraintAnnotation) {
        // Initialization logic if needed
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false; // or true if null should be considered valid (depends on your requirements)
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}