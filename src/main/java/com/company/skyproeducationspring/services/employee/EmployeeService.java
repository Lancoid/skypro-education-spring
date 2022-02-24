package com.company.skyproeducationspring.services.employee;

import com.company.skyproeducationspring.exceptions.EmployeeAlreadyAddedException;
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
        for (Employee value : employeeList) {
            if (value != null && checkEmployee(value, firstName, lastName)) {
                throw new EmployeeAlreadyAddedException("Сотрудник '" + firstName + " " + lastName + "' уже добавлен");
            }
        }

        Employee employee = new Employee(firstName, lastName);
        employeeList.add(employee);
        log.info("EmployeeService: add {}", employee);
        log.info("EmployeeService: employeeList {}", employeeList);

        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee;

        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i) != null && checkEmployee(employeeList.get(i), firstName, lastName)) {
                employee = employeeList.get(i);
                employeeList.remove(i);
                log.info("EmployeeService: remove {}", employee);
                log.info("EmployeeService: employeeList {}", employeeList);

                return employee;
            }
        }

        throw new EmployeeNotFoundException("Сотрудник '" + firstName + " " + lastName + "' не найден");
    }

    @Override
    public Employee findOne(String firstName, String lastName) {
        for (Employee employee : employeeList) {
            if (employee != null && checkEmployee(employee, firstName, lastName)) {
                log.info("EmployeeService: find {}", employee);
                return employee;
            }
        }

        throw new EmployeeNotFoundException("Сотрудник '" + firstName + " " + lastName + "' не найден");
    }

    @Override
    public ArrayList<Employee> findAll() {
        return employeeList;
    }

    private boolean checkEmployee(Employee employee, String firstName, String lastName) {
        boolean isEqualsFirstName = employee.getFirstName().equals(firstName);
        boolean isEqualsLastName = employee.getLastName().equals(lastName);

        return isEqualsFirstName && isEqualsLastName;
    }
}
