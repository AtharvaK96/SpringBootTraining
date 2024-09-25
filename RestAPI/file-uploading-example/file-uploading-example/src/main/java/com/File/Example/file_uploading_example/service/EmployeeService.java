package com.File.Example.file_uploading_example.service;

import com.File.Example.file_uploading_example.model.Emploi;
import com.File.Example.file_uploading_example.repository.EmploiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

        @Autowired
        private EmploiRepository emploiRepository;

        @Value("${file.upload-dir}")
        private String uploadDir;

        public Emploi saveEmployee(Emploi employee, MultipartFile file) throws IOException {
            validateFile(file);

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            employee.setPhotoFilePath(filePath.toString());
            return emploiRepository.save(employee);
        }

        private void validateFile(MultipartFile file) {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("File is empty");
            }

            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new IllegalArgumentException("Only image files are allowed");
            }
        }

        public byte[] getEmployeePhoto(Long id) throws IOException {
            Optional<Emploi> employeeOptional = emploiRepository.findById(id);
            if (employeeOptional.isPresent()) {
                Emploi employee = employeeOptional.get();
                Path photoPath = Paths.get(employee.getPhotoFilePath());
                return Files.readAllBytes(photoPath);
            } else {
                throw new IOException("Employee not found or no photo available");
            }
        }

        public Emploi findEmployeeById(Long id) {
            return emploiRepository.findById(id).orElse(null);
        }
    }



