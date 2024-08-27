package com.example.assignment_two.service;

import com.example.assignment_two.exception.UserNotFoundException;
import com.example.assignment_two.model.Employee;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    List<Employee> employees = new ArrayList<>(List.of(
            new Employee(1, "Mehvish", "mehvish@gmail.com", "Mehvish@123"),
            new Employee(2, "Mitali", "mitali@gmail.com", "Mitali@123")));

    public List<Employee> addEmployee(Employee employee) {
        if (employees.stream().noneMatch(emp -> emp.getEmp_id() == employee.getEmp_id())) {
            employees.add(employee);
        }
        else {
            throw new IllegalArgumentException("Employee ID must be unique");
        }
        return employees;
    }

    public List<Employee> getAllEmployee() {
        return employees;
    }

    public List<Employee> updateEmployee(int id, Employee employee) {
        boolean isUpdated = employees.stream()
                .filter(emp -> emp.getEmp_id() == id)
                .findFirst()
                .map(emp -> {emp.setEmp_name(employee.getEmp_name());
                    emp.setEmail_id(employee.getEmail_id());
                    return true;
                })
                .orElse(false);

        if (!isUpdated) {
            throw new UserNotFoundException("Employee with ID " + id + " not found");
        }
        return employees;
    }

    public List<Employee> deleteEmployee(int id) {
        boolean isRemoved = employees.removeIf(emp -> emp.getEmp_id() == id);

        if (!isRemoved) {
            throw new UserNotFoundException("Employee with ID " + id + " not found");
        }
        return employees;
    }
}