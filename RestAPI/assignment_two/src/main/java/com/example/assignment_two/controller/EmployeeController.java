package com.example.assignment_two.controller;

import com.example.assignment_two.model.Employee;
import com.example.assignment_two.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public List<Employee> addEmp(@RequestBody @Valid Employee employee){
            return employeeService.addEmployee(employee);
    }

    @GetMapping("/read")
    public List<Employee> getEmps(){
        return employeeService.getAllEmployee();
    }

    @PutMapping("/update/{id}")
    public List<Employee> updateEmp(@PathVariable int id, @RequestBody @Valid Employee employee){
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/delete/{id}")
    public List<Employee> deleteEmp(@PathVariable int id){
        return employeeService.deleteEmployee(id);
    }
}
