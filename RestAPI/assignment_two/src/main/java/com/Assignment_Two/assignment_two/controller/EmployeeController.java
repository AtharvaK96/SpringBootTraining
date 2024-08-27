package com.Assignment_Two.assignment_two.controller;

import com.Assignment_Two.assignment_two.model.EmployeeInformation;
import com.Assignment_Two.assignment_two.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class EmployeeController {
    @Autowired
    private EmployeeService e;

    @GetMapping("/emp")
    public List<EmployeeInformation> show(){
        return e.show();
    }

    @PostMapping("/add")
    public List<EmployeeInformation> addEmp( @Valid @RequestBody EmployeeInformation employeeInformation) {
        e.addEmp(employeeInformation);
        return e.show();
    }

    @PutMapping("/update/{id}")
    public List<EmployeeInformation> updateEmployee(@PathVariable int id, @Valid @RequestBody EmployeeInformation employeeInformation ){
       return e.updateEmployee(id, employeeInformation);
    }
    @DeleteMapping("/delete/{id}")
    public List<EmployeeInformation> deleteEmployee(@PathVariable int id, @RequestBody EmployeeInformation employeeInformation){
        return e.deleteEmployee(id);
    }


}
