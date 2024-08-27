package com.example.assignment_two.service;

import com.example.assignment_two.model.EmployeeModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
class UserNotFoundException extends RuntimeException {
    UserNotFoundException(String s) {
        super(s);
    }
}

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
        for (int i = 0; i < employeeModels.size(); i++) {
            if (employeeModels.get(i).getId() == id) {
                employeeModels.set(i, new EmployeeModel(id, employeeModel.getName(), employeeModel.getEmail(), employeeModel.getPassword()));
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
