//package com.Weekly.Test.weekly.test.validation;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.*;
//
//@Target({ElementType.FIELD, ElementType.PARAMETER})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@Constraint(validatedBy = ValidatePassword.class)
//public @interface ValidateTheEmployeePassword {
//
//    public @interface ValidateEmployeePassword {
//        String message() default "Password should be strong";
//        Class<?>[] groups() default {};
//        Class<? extends Payload>[] payload() default {};
//
//    }
//}
package com.Weekly.Test.weekly.test.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidatePassword.class)
public @interface ValidateTheEmployeePassword {
    String message() default "Password should be strong";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

