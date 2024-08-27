package com.Assignment_Two.assignment_two.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidatePassword.class)
public @interface ValidateEmployeePassword {
    String message() default "Password should be strong";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

