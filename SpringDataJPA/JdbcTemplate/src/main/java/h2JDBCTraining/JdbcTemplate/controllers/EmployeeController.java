package h2JDBCTraining.JdbcTemplate.controllers;

import h2JDBCTraining.JdbcTemplate.models.Employee;
import h2JDBCTraining.JdbcTemplate.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployee(){
        return  employeeService.getAllEmployee();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        return  employeeService.addEmployee(employee);
    }
    @PutMapping("/{id}")
    public Employee updateEmployee(@RequestBody Employee employee,@PathVariable("id") int id){
        return  employeeService.updateEmployee(employee,id);
    }
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable("id") int id){
        return  employeeService.deleteEmployee(id);
    }
}
