package com.company.skyproeducationspring.services.department;

import com.company.skyproeducationspring.exceptions.EmployeeNotFoundException;
import com.company.skyproeducationspring.models.Employee;
import com.company.skyproeducationspring.services.employee.EmployeeServiceInterface;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService implements DepartmentServiceInterface {
    private final EmployeeServiceInterface employeeService;

    public DepartmentService(EmployeeServiceInterface employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee minSalary(int departmentId) {
        return this.employeeService
                .findAll()
                .stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с минимальной зарплатой не найден"));
    }

    @Override
    public Employee maxSalary(int departmentId) {
        return this.employeeService
                .findAll()
                .stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с максимальной зарплатой не найден"));
    }


    @Override
    public Map<Integer, List<Employee>> findAll() {
        Map<Integer, List<Employee>> result = new HashMap<>();

        for (Employee employee : this.employeeService.findAll()) {
            result.computeIfAbsent(employee.getDepartment(), k -> new ArrayList<>()).add(employee);
        }

        return result;
    }

    @Override
    public Map<Integer, List<Employee>> findAll(int departmentId) {
        Map<Integer, List<Employee>> result = new HashMap<>();

        result.put(
                departmentId,
                this.employeeService
                        .findAll()
                        .stream()
                        .filter(employee -> departmentId == employee.getDepartment())
                        .collect(Collectors.toCollection(ArrayList::new))
        );


        return result;
    }
}
