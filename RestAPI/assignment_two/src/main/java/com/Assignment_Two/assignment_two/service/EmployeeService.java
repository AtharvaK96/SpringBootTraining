
package com.Assignment_Two.assignment_two.service;

import com.Assignment_Two.assignment_two.model.EmployeeInformation;
import com.Assignment_Two.assignment_two.exception.EmployeeNotFoundException; // Custom exception class
import com.Assignment_Two.assignment_two.exception.DuplicateEmployeeIdException; // Custom exception class
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private List<EmployeeInformation> employeeModels = new ArrayList<>(List.of(
            new EmployeeInformation(101, "Heena", "heena@benchmarkit.solutions", "Abc@1231"),
            new EmployeeInformation(102, "Zainab", "zainab@benchmarkit.solutions", "Srt@4564")
    ));

    public List<EmployeeInformation> show() {
        return new ArrayList<>(employeeModels);
    }

    public void addEmp(EmployeeInformation employeeInformation) {
        if (employeeInformation == null) {
            throw new IllegalArgumentException("Employee information cannot be null");
        }


        for (EmployeeInformation employee : employeeModels) {
            if (employee.getEmp_id() == employeeInformation.getEmp_id()) {
                throw new DuplicateEmployeeIdException("Employee with id " + employeeInformation.getEmp_id() + " already exists");
            }
        }

        employeeModels.add(employeeInformation);
    }

    public List<EmployeeInformation> updateEmployee(int id, EmployeeInformation employeeInformation) {
        if (employeeInformation == null) {
            throw new IllegalArgumentException("Employee information cannot be null");
        }


        boolean idConflict = employeeModels.stream()
                .anyMatch(emp -> emp.getEmp_id() == employeeInformation.getEmp_id() && emp.getEmp_id() != id);
        if (idConflict) {
            throw new DuplicateEmployeeIdException("Employee with id " + employeeInformation.getEmp_id() + " already exists");
        }


        boolean updated = false;
        for (int i = 0; i < employeeModels.size(); i++) {
            if (employeeModels.get(i).getEmp_id() == id) {
                employeeModels.set(i, employeeInformation);
                updated = true;
                break;
            }
        }

        if (!updated) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }

        return new ArrayList<>(employeeModels);
    }

    public List<EmployeeInformation> deleteEmployee(int id) {
        boolean removed = false;
        for (int i = 0; i < employeeModels.size(); i++) {
            if (employeeModels.get(i).getEmp_id() == id) {
                employeeModels.remove(i);
                removed = true;
                break;
            }
        }

        if (!removed) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }

        return new ArrayList<>(employeeModels);
    }
}

