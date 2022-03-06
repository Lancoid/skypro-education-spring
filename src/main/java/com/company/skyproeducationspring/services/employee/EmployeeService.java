package com.company.skyproeducationspring.services.employee;

import com.company.skyproeducationspring.exceptions.EmployeeAlreadyAddedException;
import com.company.skyproeducationspring.exceptions.EmployeeNotFoundException;
import com.company.skyproeducationspring.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@Slf4j
public class EmployeeService implements EmployeeServiceInterface {
    private final HashMap<String, Employee> employeeList;

    public EmployeeService() {
        this.employeeList = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
        if (employeeList.containsKey(generateKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException("Сотрудник '" + firstName + " " + lastName + "' уже добавлен");
        }

        Employee employee = new Employee(firstName, lastName);
        employeeList.put(generateKey(firstName, lastName), employee);

        log.info("EmployeeService: add {}", employee);
        log.info("EmployeeService: employeeList {}", employeeList);

        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = employeeList.remove(generateKey(firstName, lastName));

        if (employee != null) {
            log.info("EmployeeService: remove {}", employee);
            log.info("EmployeeService: employeeList {}", employeeList);

            return employee;
        }

        throw new EmployeeNotFoundException("Сотрудник '" + firstName + " " + lastName + "' не найден");
    }

    @Override
    public Employee findOne(String firstName, String lastName) {
        Employee employee = employeeList.get(generateKey(firstName, lastName));

        if (employee != null) {
            log.info("EmployeeService: find {}", employee);

            return employee;
        }

        throw new EmployeeNotFoundException("Сотрудник '" + firstName + " " + lastName + "' не найден");
    }

    @Override
    public ArrayList<Employee> findAll() {
        return new ArrayList<>(employeeList.values());
    }

    private String generateKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
}
