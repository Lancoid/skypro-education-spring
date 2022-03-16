package com.company.skyproeducationspring.controllers;

import com.company.skyproeducationspring.models.Employee;
import com.company.skyproeducationspring.services.employee.EmployeeServiceInterface;
import com.company.skyproeducationspring.services.employee.EmployeeValidatorServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceInterface employeeService;
    private final EmployeeValidatorServiceInterface employeeValidatorService;

    public EmployeeController(EmployeeServiceInterface employeeService, EmployeeValidatorServiceInterface employeeValidatorService) {
        this.employeeService = employeeService;
        this.employeeValidatorService = employeeValidatorService;
    }

    @GetMapping(path = "/add")
    public String add(String firstName, String lastName, int departmentId, float salary, Model model) {
        Employee employee = employeeService.add(
                this.employeeValidatorService.validateFirstName(firstName),
                this.employeeValidatorService.validateLastName(lastName),
                departmentId,
                salary
        );

        model.addAttribute("employee", employee);

        log.info("EmployeeController: add {}", employee);
        log.info("EmployeeController: employeeList {}", employeeService.findAll());

        return "employee/add";
    }

    @GetMapping(path = "/remove")
    public String remove(String firstName, String lastName, Model model) {
        Employee employee = employeeService.remove(
                this.employeeValidatorService.validateFirstName(firstName),
                this.employeeValidatorService.validateLastName(lastName)
        );

        model.addAttribute("employee", employee);

        log.info("EmployeeController: remove {}", employee);
        log.info("EmployeeController: employeeList {}", employeeService.findAll());

        return "employee/remove";
    }

    @GetMapping(path = "/find")
    public String find(String firstName, String lastName, Model model) {
        Employee employee = employeeService.findOne(
                this.employeeValidatorService.validateFirstName(firstName),
                this.employeeValidatorService.validateLastName(lastName)
        );

        model.addAttribute("employee", employee);

        log.info("EmployeeController: find {}", employee);

        return "employee/find";
    }

    @GetMapping(path = "/find-all")
    public String findAll(Model model) {
        ArrayList<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employees", employeeList);

        log.info("EmployeeController: employeeList {}", employeeList);

        return "employee/find-all";
    }
}
