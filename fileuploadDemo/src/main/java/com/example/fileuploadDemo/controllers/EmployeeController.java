package com.example.fileuploadDemo.controllers;

import com.example.fileuploadDemo.models.Employee;
import com.example.fileuploadDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping
    public String addEmployee(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("file") MultipartFile file
            ){
        Employee employee = Employee.builder().email(email).name(name).build();
        Employee savedEmployee = employeeService.addEmployee(employee, file);
        return "redirect:/empDetails/"+savedEmployee.getId();

    }
    @GetMapping
    public List<Employee> getEmployee(){
        return employeeService.getAllEmployee();
    }


    @GetMapping("/{id}/photo")
    public ResponseEntity<byte[]> getEmployeePhoto(@PathVariable("id") Integer id){
        return employeeService.findEmployeePhoto(id);
    }
}
