package com.example.assignment_two.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Employee {
    private int emp_id;
    @NotEmpty(message = "Employee name cannot be empty")
    private String emp_name;
    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email cannot be empty")
    private String email_id;

    public Employee(int emp_id, String emp_name, String email_id) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.email_id = email_id;
    }
}
