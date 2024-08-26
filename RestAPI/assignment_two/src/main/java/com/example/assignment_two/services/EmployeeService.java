package com.example.assignment_two.services;

import com.example.assignment_two.exceptions.EmployeeAlreadyExistsException;
import com.example.assignment_two.exceptions.EmployeeNotFoundException;
import com.example.assignment_two.models.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private List<Employee> employeeList;

    public EmployeeService() {
        this.employeeList = new ArrayList<>(List.of(
                new Employee(1,"Sahil","sahil@abc.com","benchmark 1"),
                new Employee(2,"Bob","bob@abc.com","benchmark 2"),
                new Employee(3,"john","john@abc.com","benchmark 2")
        ));

    }
    public String addEmployee(Employee emp){
                employeeList.stream()
                .filter((e) -> e.getId() == emp.getId() || e.getEmail() == emp.getEmail())
                .findFirst()
                .ifPresent((e)->{
            throw new EmployeeAlreadyExistsException("Employee already exists");
        });
        employeeList.add(emp);
        return "Employee added successfully";


    }
     public List<Employee> getEmployee(){
        return employeeList;
     }
     public Employee updateEmployee(Employee emp,int empId){
         Employee employee = employeeList.stream()
                 .filter((e) -> e.getId() == empId)
                 .findFirst()
                 .map(employee1 -> {
                     employee1.setName(emp.getName());
                     employee1.setEmail(emp.getEmail());
                     employee1.setCompany(emp.getCompany());

                     return employee1;
                 })
                 .orElseThrow(() -> new EmployeeNotFoundException("No such Employee present"));


//        employeeList.add(employee);
        return employee;

     }

     public String deleteEmployee(int id){
         Employee emp = employeeList.stream().filter(e -> e.getId() == id).findFirst().orElseThrow(() -> new EmployeeNotFoundException("Employee does not exists"));
         employeeList.remove(emp);
         return "Employee deleted successfully";
     }

}
