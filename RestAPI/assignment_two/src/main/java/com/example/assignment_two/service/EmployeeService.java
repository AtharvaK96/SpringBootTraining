package com.example.assignment_two.service;

import com.example.assignment_two.exception.UserNotFoundException;
import com.example.assignment_two.model.EmployeeModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    int i=4;
    List<EmployeeModel> employeeModels=new ArrayList<>(List.of(
            new EmployeeModel( 1, "Mitali", "mitali@benchmarkit.solutions", "Mitali@123"),
            new EmployeeModel( 2, "Heena", "heena@benchmarkit.solutions", "Heena@123"),
            new EmployeeModel(3, "Mehvish","mehvish@benchmarkit.solution", "Mehvish@123")
    ));

    public List<EmployeeModel> displayEmployees() {
        return employeeModels;
    }

    public List<EmployeeModel> addEmployee(EmployeeModel employeeModel) {
        employeeModels.add(new EmployeeModel(i, employeeModel.getName(), employeeModel.getEmail(), employeeModel.getPassword()));
        i++;
        return employeeModels;
    }

    public List<EmployeeModel> updateEmployee(int id, EmployeeModel employeeModel) {
        boolean isUpdated = employeeModels.stream().filter(e -> e.getId() == id).findFirst().map(e -> {
            employeeModels.set(employeeModels.indexOf(e), new EmployeeModel(id, employeeModel.getName(), employeeModel.getEmail(), employeeModel.getPassword()));
            return true;
        }).orElse(false);
        if(!isUpdated) {
            throw new UserNotFoundException("User not found");
        }
        return employeeModels;
    }

    public List<EmployeeModel> deleteEmployee(int id) {
        boolean isDeleted = employeeModels.removeIf(e -> e.getId() == id);
        if(!isDeleted) {
            throw new UserNotFoundException("User not found");
        }
        return employeeModels;
    }
}
