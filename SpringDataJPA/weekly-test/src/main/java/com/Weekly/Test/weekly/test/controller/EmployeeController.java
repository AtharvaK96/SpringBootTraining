package com.Weekly.Test.weekly.test.controller;

import com.Weekly.Test.weekly.test.model.Employ;
import com.Weekly.Test.weekly.test.service.EmployeeService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@Validated
public class EmployeeController {

private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


@PostMapping
public ResponseEntity<String> addEmployee(@Valid @RequestBody Employ employee) {
    employeeService.addEmployee(employee);
    return new ResponseEntity<>("Employee added successfully", HttpStatus.CREATED);
}


    @PutMapping
    public ResponseEntity<String> updateEmployee(@RequestBody Employ employee) {
        employeeService.updateEmployee(employee);
        return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employ> getEmployeeById(@PathVariable Long id) {
        Employ employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Employ>> getAllEmployees() {
        List<Employ> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        Employ employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }




}


