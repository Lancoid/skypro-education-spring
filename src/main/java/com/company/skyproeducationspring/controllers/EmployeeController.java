package com.company.skyproeducationspring.controllers;

import com.company.skyproeducationspring.models.Employee;
import com.company.skyproeducationspring.services.employee.EmployeeServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceInterface employeeService;

    public EmployeeController(EmployeeServiceInterface employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public String add(String firstName, String lastName) {
        Employee employee = employeeService.add(firstName, lastName);

        return "Сотрудник добавлен : " + employee;
    }

    @GetMapping(path = "/remove")
    public String remove(String firstName, String lastName) {
        Employee employee = employeeService.remove(firstName, lastName);

        return "Сотрудник удалён : " + employee;
    }

    @GetMapping(path = "/find")
    public String find(String firstName, String lastName) {
        Employee employee = employeeService.find(firstName, lastName);

        return "Сотрудник найден : " + employee;
    }
}
