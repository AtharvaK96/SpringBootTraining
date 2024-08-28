package h2JDBCTraining.JdbcTemplate.services;

import h2JDBCTraining.JdbcTemplate.models.Employee;
import h2JDBCTraining.JdbcTemplate.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
     public List<Employee> getAllEmployee(){
        return employeeRepository.findAllEmployee();
     }
     public Employee addEmployee(Employee employee){
        return employeeRepository.addEmployee(employee);
     }
     public Employee updateEmployee(Employee employee ,int id){
        return employeeRepository.updateEmployee(employee,id);


     }
     public String deleteEmployee(int id){
        return employeeRepository.deleteEmployee(id);
     }

}
