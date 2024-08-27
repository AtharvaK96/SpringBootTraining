package com.example.assignment_two.controller;

import com.example.assignment_two.model.EmployeeModel;
import com.example.assignment_two.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/display")
    public List<EmployeeModel> displayEmployees() {
        return employeeService.displayEmployees();
    }

    @PostMapping("/add")
    public List<EmployeeModel> addEmployee(@Valid @RequestBody EmployeeModel employeeModel) {
        return employeeService.addEmployee(employeeModel);
    }

    @PutMapping("/update/{id}")
    public List<EmployeeModel> updateEmployee(@PathVariable int id, @Valid @RequestBody EmployeeModel employeeModel) {
        return employeeService.updateEmployee(id, employeeModel);
    }

    @DeleteMapping("/delete/{id}")
    public List<EmployeeModel> deleteEmployee(@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }
}
