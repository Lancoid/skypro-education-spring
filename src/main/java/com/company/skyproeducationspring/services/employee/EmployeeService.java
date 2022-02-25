package com.company.skyproeducationspring.services.employee;

import com.company.skyproeducationspring.exceptions.EmployeeAlreadyAddedException;
import com.company.skyproeducationspring.exceptions.EmployeeNotAddedException;
import com.company.skyproeducationspring.exceptions.EmployeeNotFoundException;
import com.company.skyproeducationspring.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class EmployeeService implements EmployeeServiceInterface {
    private final ArrayList<Employee> employeeList;

    public EmployeeService() {
        this.employeeList = new ArrayList<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (employeeList.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник '" + firstName + " " + lastName + "' уже добавлен");
        }

        if (employeeList.add(employee)) {
            log.info("EmployeeService: add {}", employee);
            log.info("EmployeeService: employeeList {}", employeeList);

            return employee;
        }

        throw new EmployeeNotAddedException("Нет удалось добавить сотрудника");
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (employeeList.remove(employee)) {
            log.info("EmployeeService: remove {}", employee);
            log.info("EmployeeService: employeeList {}", employeeList);

            return employee;
        }

        throw new EmployeeNotFoundException("Сотрудник '" + firstName + " " + lastName + "' не найден");
    }

    @Override
    public Employee findOne(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (employeeList.contains(employee)) {
            log.info("EmployeeService: find {}", employee);

            return employee;
        }

        throw new EmployeeNotFoundException("Сотрудник '" + firstName + " " + lastName + "' не найден");
    }

    @Override
    public ArrayList<Employee> findAll() {
        return new ArrayList<>(employeeList);
    }
}
