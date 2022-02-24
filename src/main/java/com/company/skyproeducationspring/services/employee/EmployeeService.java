package com.company.skyproeducationspring.services.employee;

import com.company.skyproeducationspring.exceptions.EmployeeAlreadyAddedException;
import com.company.skyproeducationspring.exceptions.EmployeeNotAddedException;
import com.company.skyproeducationspring.exceptions.EmployeeNotFoundException;
import com.company.skyproeducationspring.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class EmployeeService implements EmployeeServiceInterface {
    private final Employee[] employeeList;

    public EmployeeService() {
        this.employeeList = new Employee[5];
    }

    @Override
    public Employee add(String firstName, String lastName) {
        Integer id = null;

        for (int i = 0; i < employeeList.length; i++) {
            if (employeeList[i] != null && checkEmployee(employeeList[i], firstName, lastName)) {
                throw new EmployeeAlreadyAddedException("Сотрудник '" + firstName + " " + lastName + "' уже добавлен");
            }

            if (employeeList[i] == null && id == null) {
                id = i;
            }
        }

        if (id != null) {
            employeeList[id] = new Employee(firstName, lastName);
            log.info("EmployeeService: add {}", employeeList[id]);
            log.info("EmployeeService: employeeList {}", Arrays.toString(employeeList));

            return employeeList[id];
        }

        throw new EmployeeNotAddedException("Нет места для добавления нового сотрудника");
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee;

        for (int i = 0; i < employeeList.length; i++) {
            if (employeeList[i] != null && checkEmployee(employeeList[i], firstName, lastName)) {
                employee = employeeList[i];
                employeeList[i] = null;
                log.info("EmployeeService: remove {}", employee);
                log.info("EmployeeService: employeeList {}", Arrays.toString(employeeList));

                return employee;
            }
        }

        throw new EmployeeNotFoundException("Сотрудник '" + firstName + " " + lastName + "' не найден");
    }

    @Override
    public Employee find(String firstName, String lastName) {
        for (Employee employee : employeeList) {
            if (employee != null && checkEmployee(employee, firstName, lastName)) {
                log.info("EmployeeService: find {}", employee);
                return employee;
            }
        }

        throw new EmployeeNotFoundException("Сотрудник '" + firstName + " " + lastName + "' не найден");
    }

    private boolean checkEmployee(Employee employee, String firstName, String lastName) {
        boolean isEqualsFirstName = employee.getFirstName().equals(firstName);
        boolean isEqualsLastName = employee.getLastName().equals(lastName);

        return isEqualsFirstName && isEqualsLastName;
    }
}
