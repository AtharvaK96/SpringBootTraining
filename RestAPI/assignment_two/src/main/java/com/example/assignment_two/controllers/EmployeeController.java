package com.example.assignment_two.controllers;

import com.example.assignment_two.models.Employee;
import com.example.assignment_two.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/employee")
    public List<Employee> getEmployees(){
        return employeeService.getEmployee();
    }

    @PostMapping("/employee")
    public Employee addEmployee(@Valid  @RequestBody Employee newEmployee){
        employeeService.addEmployee(newEmployee);
        return newEmployee;
    }
    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@Valid @RequestBody Employee emp,@PathVariable("id") int id){
        return employeeService.updateEmployee(emp,id);
    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable("id") int id){
        return employeeService.deleteEmployee(id);
    }





}
