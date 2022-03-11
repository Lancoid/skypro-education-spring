package com.company.skyproeducationspring.controllers;

import com.company.skyproeducationspring.models.Employee;
import com.company.skyproeducationspring.services.employee.EmployeeServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/department")
public class DepartmentController {
    private final EmployeeServiceInterface employeeService;

    public DepartmentController(EmployeeServiceInterface employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/max-salary")
    public String maxSalary(int departmentId, Model model) {
        Employee employee = employeeService.maxSalary(departmentId);
        model.addAttribute("employee", employee);

        log.info("DepartmentController: maxSalary {}", employee);

        return "department/max-salary";
    }

    @GetMapping(path = "/min-salary")
    public String minSalary(int departmentId, Model model) {
        Employee employee = employeeService.minSalary(departmentId);
        model.addAttribute("employee", employee);

        log.info("DepartmentService: minSalary {}", employee);

        return "department/min-salary";
    }

    @GetMapping(path = "/all?departmentId=\\d+")
    public String allByDepartmentId(int departmentId, Model model) {
        ArrayList<Employee> employees = employeeService.findAll(departmentId);
        model.addAttribute("employees", employees);

        log.info("DepartmentController: allByDepartment " + departmentId + " {}", employees);

        return "department/all";
    }

    @GetMapping(path = "/all")
    public String findAll(Model model) {
        ArrayList<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);

        log.info("DepartmentController: allByDepartments {}", employees);

        return "department/all";
    }
}
