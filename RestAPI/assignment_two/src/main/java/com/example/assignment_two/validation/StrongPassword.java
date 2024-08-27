package com.example.assignment_two.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StrongPasswordValidation.class)
public @interface StrongPassword {
    public String message() default "Enter strong password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
