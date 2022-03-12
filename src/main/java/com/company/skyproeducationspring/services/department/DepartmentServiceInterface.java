package com.company.skyproeducationspring.services.department;

import com.company.skyproeducationspring.models.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentServiceInterface {
    Employee minSalary(int departmentId);

    Employee maxSalary(int departmentId);

    Map<Integer, List<Employee>> findAll();

    Map<Integer, List<Employee>> findAll(int departmentId);
}
