package com.example.UploadImage.controller;

import com.example.UploadImage.model.Employee;
import com.example.UploadImage.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.IOException;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload_form";
    }

    @PostMapping("/upload")
    public String uploadEmployee(@RequestParam("name") String name,
                                 @RequestParam("email") String email,
                                 @RequestParam("photo") MultipartFile photo,
                                 Model model) {
        try {
            Employee employee = new Employee();
            employee.setName(name);
            employee.setEmail(email);

            Employee savedEmployee = employeeService.saveEmployee(employee, photo);
            model.addAttribute("employee", savedEmployee);

            return "redirect:/employees/details/" + savedEmployee.getId();
        } catch (IOException e) {
            model.addAttribute("error", "File upload failed: " + e.getMessage());
            return "upload_form";
        }
    }

    @GetMapping("/details/{id}")
    public String showEmployeeDetails(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            model.addAttribute("error", "Employee not found");
            return "upload_form";
        }
        model.addAttribute("employee", employee);
        return "employee_details";
    }
}


