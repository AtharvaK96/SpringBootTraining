package com.example.assignment_two.service;

import com.example.assignment_two.model.EmployeeModel;
import org.springframework.stereotype.Service;

import java.util.*;
class UserNotFoundException extends RuntimeException {
    UserNotFoundException(String s) {
        super(s);
    }
}

@Service
public class EmployeeService {
    int i=4;
    List<EmployeeModel> employeeModels=new ArrayList<>(List.of(
            new EmployeeModel( 1, "Mitali", "mitali@benchmarkit.solutions"),
            new EmployeeModel( 2, "Heena", "heena@benchmarkit.solutions"),
            new EmployeeModel(3, "Mehvish","mehvish@benchmarkit.solution")
    ));

    public List<EmployeeModel> displayEmployees() {
        return employeeModels;
    }

    public List<EmployeeModel> addEmployee(EmployeeModel employeeModel) {
        employeeModels.add(new EmployeeModel(i, employeeModel.getName(), employeeModel.getEmail()));
        i++;
        return employeeModels;
    }

    public List<EmployeeModel> updateEmployee(int id, EmployeeModel employeeModel) {
        for (int i = 0; i < employeeModels.size(); i++) {
            if (employeeModels.get(i).getId() == id) {
                employeeModels.set(i, new EmployeeModel(id, employeeModel.getName(), employeeModel.getEmail()));
                return employeeModels;
            }
        }
        throw new UserNotFoundException("User not found");
    }

    public List<EmployeeModel> deleteEmployee(int id) {
        for(int i=0;i<employeeModels.size();i++) {
            if(employeeModels.get(i).getId()==id) {
                employeeModels.remove(i);
                return employeeModels;
            }
        }
        throw new UserNotFoundException("User not found");
    }
}
