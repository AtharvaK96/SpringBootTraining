package com.example.upload_image.controller;

import com.example.upload_image.model.Employee;
import com.example.upload_image.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload";
    }

    @PostMapping("/upload-photo")
    public String uploadPhoto(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("photo") MultipartFile photo,
            Model model) {
        try {
            Employee employee = new Employee();
            employee.setName(name);
            employee.setEmail(email);

            Employee savedEmployee = employeeService.saveEmployee(employee, photo);

            model.addAttribute("employee", savedEmployee);

            return "employee-details";
        } catch (IOException e) {
            model.addAttribute("error", "File upload failed: " + e.getMessage());
            return "upload";
        }
    }
}
