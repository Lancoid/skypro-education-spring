package com.company.skyproeducationspring.services.employee;

import com.company.skyproeducationspring.models.Employee;

import java.util.ArrayList;

public interface EmployeeServiceInterface {

    Employee add(String firstName, String lastName);

    Employee remove(String firstName, String lastName);

    Employee findOne(String firstName, String lastName);

    ArrayList<Employee> findAll();
}
