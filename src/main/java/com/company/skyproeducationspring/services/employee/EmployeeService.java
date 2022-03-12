package com.company.skyproeducationspring.services.employee;

import com.company.skyproeducationspring.exceptions.EmployeeAlreadyAddedException;
import com.company.skyproeducationspring.exceptions.EmployeeNotFoundException;
import com.company.skyproeducationspring.models.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class EmployeeService implements EmployeeServiceInterface {
    private final HashMap<String, Employee> employeeList;

    public EmployeeService() {
        this.employeeList = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName, int department, float salary) {
        if (employeeList.containsKey(generateKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException("Сотрудник '" + firstName + " " + lastName + "' уже добавлен");
        }

        Employee employee = new Employee(firstName, lastName, department, salary);
        employeeList.put(generateKey(firstName, lastName), employee);

        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = employeeList.remove(generateKey(firstName, lastName));

        if (employee != null) {
            return employee;
        }

        throw new EmployeeNotFoundException("Сотрудник '" + firstName + " " + lastName + "' не найден");
    }

    @Override
    public Employee findOne(String firstName, String lastName) {
        Employee employee = employeeList.get(generateKey(firstName, lastName));

        if (employee != null) {
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
