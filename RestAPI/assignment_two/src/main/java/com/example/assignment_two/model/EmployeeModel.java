package com.example.assignment_two.model;

import com.example.assignment_two.validation.StrongPassword;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("emailId")
    private final String email;

    @StrongPassword
    private final String password;
}
