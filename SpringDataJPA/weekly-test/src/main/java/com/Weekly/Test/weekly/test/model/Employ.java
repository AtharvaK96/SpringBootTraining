package com.Weekly.Test.weekly.test.model;

import com.Weekly.Test.weekly.test.validation.ValidateTheEmployeePassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data

public class Employ {
    @NotNull(message = "Employee ID cannot be null.")

    private Long emp_id;

    private  String name;
    @NotEmpty(message = "Email cannot be empty.")
    @Email(message= "Email cannot be empty.")
    private  String email;
    @ValidateTheEmployeePassword
    private String password;




}
