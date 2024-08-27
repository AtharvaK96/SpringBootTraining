package com.Assignment_Two.assignment_two.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Data
@RequiredArgsConstructor


public class EmployeeInformation {
    @NotNull(message = "Employee ID cannot be null.")
    private final int emp_id;
    private final String name;
    @NotEmpty(message = "Email cannot be empty.")
    @Email(message= "Email cannot be empty.")
    private final String email;





    }

