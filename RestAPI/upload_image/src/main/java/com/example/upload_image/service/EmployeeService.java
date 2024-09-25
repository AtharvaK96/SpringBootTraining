package com.example.upload_image.service;

import com.example.upload_image.model.Employee;
import com.example.upload_image.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public Employee saveEmployee(Employee employee, MultipartFile file) throws IOException {
        String fileType = file.getContentType();
        if (!(fileType.equals("image/jpeg") || fileType.equals("image/png"))) {
            throw new IllegalArgumentException("Invalid file type. Only JPEG or PNG is allowed.");
        }
        if (file.getSize() > 5242880) {
            throw new IllegalArgumentException("File size exceeds 5MB limit.");
        }
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        employee.setPhotoFilePath("/uploads/"+fileName);
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
