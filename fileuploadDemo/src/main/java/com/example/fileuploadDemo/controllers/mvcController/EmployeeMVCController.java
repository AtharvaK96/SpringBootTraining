package com.example.fileuploadDemo.controllers.mvcController;

import com.example.fileuploadDemo.models.Employee;
import com.example.fileuploadDemo.repository.EmployeeRepository;
import com.example.fileuploadDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeMVCController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping("/upload")
    public String handleUpload(){
        System.out.println("running upload details");
        return "uploadDetail";
    }
    @RequestMapping("/empDetails/{id}")
    public String showEmpDetails(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("No such employeee"));
        model.addAttribute("employee",employee);
        String[] imagePathSplit = employee.getPhotoFilePath().split("\\\\");

        model.addAttribute("employeeImg",imagePathSplit[1]);

        System.out.println("running details");
        return "details";
    }

}
