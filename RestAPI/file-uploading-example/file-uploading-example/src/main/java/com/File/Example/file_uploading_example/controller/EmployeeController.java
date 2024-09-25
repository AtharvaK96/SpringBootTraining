package com.File.Example.file_uploading_example.controller;

import com.File.Example.file_uploading_example.model.Emploi;
import com.File.Example.file_uploading_example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

        @Autowired
        private EmployeeService employeeService;

        @PostMapping("/upload-photo")
        public ResponseEntity<String> uploadPhoto(
                @RequestParam("name") String name,
                @RequestParam("email") String email,
                @RequestParam("photo") MultipartFile photo) {
            try {
                Emploi employee = new Emploi();
                employee.setName(name);
                employee.setEmail(email);

                Emploi savedEmployee = employeeService.saveEmployee(employee, photo);

                return ResponseEntity.ok("Employee saved with ID: " + savedEmployee.getId());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("File upload failed: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error: " + e.getMessage());
            }
        }

    @GetMapping("/{id}/photo")
        public ResponseEntity<byte[]> getEmployeePhoto(@PathVariable Long id) {
            try {
                byte[] photo = employeeService.getEmployeePhoto(id);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);
                return new ResponseEntity<>(photo, headers, HttpStatus.OK);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        }
    }
