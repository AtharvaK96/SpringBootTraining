package com.Weekly.Test.weekly.test.service;


import com.Weekly.Test.weekly.test.model.Employ;
import com.Weekly.Test.weekly.test.repository.EmployeeRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmployeeService(EmployeeRepository employeeRepository, JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.employeeRepository = employeeRepository;
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void addEmployee(Employ employee) {
        employeeRepository.addEmployee(employee);
        sendEmailNotification("heenadongarkar@benchmarkit.solutions", "Employee Added", "employee-added");

    }

    public void updateEmployee(Employ employee) {
        employeeRepository.updateEmployee(employee);
        sendEmailNotification("heenadongarkar@benchmarkit.solutions", "Employee Updated", "employee-updated");

    }

    public Employ getEmployeeById(Long emp_id) {
        return employeeRepository.getEmployeeById(emp_id);
    }

    public List<Employ> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public void deleteEmployee(Long emp_id) {
        Employ employee = employeeRepository.getEmployeeById(emp_id);
        employeeRepository.deleteEmployee(emp_id);
        sendEmailNotification("heenadongarkar@benchmarkit.solutions", "Employee Deleted", "employee-deleted");

    }

    private void sendEmailNotification(String to, String subject, String templateName) {
        try {
            Context context = new Context();
            context.setVariable("name", "Employee");
            String body = templateEngine.process(templateName, context);

            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(body, true);

            mailSender.send(messageHelper.getMimeMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
