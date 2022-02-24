package com.company.skyproeducationspring.services.employee;

import com.company.skyproeducationspring.models.Employee;

public interface EmployeeServiceInterface {

    Employee add(String firstName, String lastName);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);
}
