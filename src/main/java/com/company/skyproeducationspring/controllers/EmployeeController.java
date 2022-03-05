package com.company.skyproeducationspring.controllers;

import com.company.skyproeducationspring.services.employee.EmployeeServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceInterface employeeService;

    public EmployeeController(EmployeeServiceInterface employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public String add(String firstName, String lastName, Model model) {
        model.addAttribute("employee", employeeService.add(firstName, lastName));

        return "employee/add";
    }

    @GetMapping(path = "/remove")
    public String remove(String firstName, String lastName, Model model) {
        model.addAttribute("employee", employeeService.remove(firstName, lastName));

        return "employee/remove";
    }

    @GetMapping(path = "/find")
    public String find(String firstName, String lastName, Model model) {
        model.addAttribute("employee", employeeService.findOne(firstName, lastName));

        return "employee/find";
    }

    @GetMapping(path = "/find-all")
    public String findAll(Model model) {
        model.addAttribute("employees", employeeService.findAll());

        return "employee/find-all";
    }
}
