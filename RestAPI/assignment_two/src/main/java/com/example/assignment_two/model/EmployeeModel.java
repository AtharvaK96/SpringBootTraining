package com.example.assignment_two.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EmployeeModel {
    private final int id;

    @NotEmpty(message = "Name is mandatory")
    private final String name;

    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private final String email;
}
