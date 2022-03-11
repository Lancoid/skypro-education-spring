package com.company.skyproeducationspring.services.employee;

import com.company.skyproeducationspring.models.Employee;

import java.util.ArrayList;

public interface EmployeeServiceInterface {
    Employee add(String firstName, String lastName, int department, float salary);

    Employee remove(String firstName, String lastName);

    Employee minSalary(int departmentId);

    Employee maxSalary(int departmentId);

    Employee findOne(String firstName, String lastName);

    ArrayList<Employee> findAll();

    ArrayList<Employee> findAll(int departmentId);
}
