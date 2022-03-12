package com.company.skyproeducationspring.controllers;

import com.company.skyproeducationspring.models.Employee;
import com.company.skyproeducationspring.services.department.DepartmentServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentServiceInterface departmentService;

    public DepartmentController(DepartmentServiceInterface departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public String maxSalary(int departmentId, Model model) {
        Employee employee = departmentService.maxSalary(departmentId);
        model.addAttribute("employee", employee);

        log.info("DepartmentController: maxSalary {}", employee);

        return "department/max-salary";
    }

    @GetMapping(path = "/min-salary")
    public String minSalary(int departmentId, Model model) {
        Employee employee = departmentService.minSalary(departmentId);
        model.addAttribute("employee", employee);

        log.info("DepartmentService: minSalary {}", employee);

        return "department/min-salary";
    }

    @GetMapping(path = "/all")
    public String all(@RequestParam(required = false) String departmentId, Model model) {
        Map<Integer, List<Employee>> employees;

        if (departmentId != null) {
            employees = departmentService.findAll(Integer.parseInt(departmentId));

            log.info("DepartmentController: allByDepartment " + departmentId + " {}", employees);
        } else {
            employees = departmentService.findAll();

            log.info("DepartmentController: allByDepartments {}", employees);
        }

        model.addAttribute("employees", employees);

        return "department/all";
    }
}
