package com.example.fileuploadDemo.service;

import com.example.fileuploadDemo.exception.FileUploadException;
import com.example.fileuploadDemo.models.Employee;
import com.example.fileuploadDemo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service

public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Value("${file.upload-dir}")
    private String uploadDir;

    private final static int MAX_FILE_SIZE = 5 * 1024 * 1024;
    private final Set<String> validFiles = new HashSet<>(Arrays.asList("image/jpeg", "image/png"));


    public Employee addEmployee(Employee emp, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Path destPath = Paths.get(uploadDir + fileName);

        //check file size and typ
        if (file.getSize() > MAX_FILE_SIZE)
            throw new FileUploadException("The size of the file should be less than 5MB");
        if (!validFiles.contains(file.getContentType())) throw new FileUploadException("Invalid file type");


        try {
            Files.copy(file.getInputStream(), destPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        emp.setPhotoFilePath(destPath.toString());
        return employeeRepository.save(emp);
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public ResponseEntity<byte[]> findEmployeePhoto(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee does Not exists"));
        String photoFilePath = employee.getPhotoFilePath();
        try {
            Path imagePath = Paths.get(photoFilePath);
            byte[] imageBytes = Files.readAllBytes(imagePath);


            HttpHeaders headers = new HttpHeaders();
            if (employee.getPhotoFilePath().endsWith(".png")) {
                headers.setContentType(MediaType.IMAGE_PNG);

            } else if (employee.getPhotoFilePath().endsWith(".jpeg") || employee.getPhotoFilePath().endsWith(".jpg")) {
                headers.setContentType(MediaType.IMAGE_JPEG);

            }
//            MediaType.I
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}




