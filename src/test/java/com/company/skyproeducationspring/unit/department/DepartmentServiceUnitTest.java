package com.company.skyproeducationspring.unit.department;

import com.company.skyproeducationspring.models.Employee;
import com.company.skyproeducationspring.services.department.DepartmentService;
import com.company.skyproeducationspring.services.employee.EmployeeServiceInterface;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.apache.commons.collections4.ListUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@DisplayName("Department Service Test")
@Epic("DepartmentService")
@ExtendWith(MockitoExtension.class)
public class DepartmentServiceUnitTest {
    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private EmployeeServiceInterface employeeServiceMock;

    @Description("Check service correct `minSalary` processing")
    @Test
    public void testCorrectMinSalary() {
        List<Employee> employeeList = getEmployeeList(1);

        when(employeeServiceMock.findAll()).thenReturn((ArrayList<Employee>) employeeList);

        assertEquals(1, employeeList.get(0).getDepartment());
        assertEquals(employeeList.get(0), departmentService.minSalary(1));
    }

    @Description("Check service correct `maxSalary` processing")
    @Test
    public void testCorrectMaxSalary() {
        List<Employee> employeeList = getEmployeeList(1);

        when(employeeServiceMock.findAll()).thenReturn((ArrayList<Employee>) employeeList);

        assertEquals(1, employeeList.get(4).getDepartment());
        assertEquals(employeeList.get(4), departmentService.maxSalary(1));
    }

    @Description("Check service correct `findAll by departmentId` processing")
    @Test
    public void testCorrectFindAllByDepartmentId() {
        Map<Integer, List<Employee>> expectedEmployeeMap = new HashMap<>();

        List<Employee> employeeListFirst = getEmployeeList(1);
        expectedEmployeeMap.put(1, employeeListFirst);

        List<Employee> employeeListSecond = getEmployeeList(2);

        ArrayList<Employee> employeeList = (ArrayList<Employee>) ListUtils.union(employeeListFirst, employeeListSecond);

        when(employeeServiceMock.findAll()).thenReturn(employeeList);

        assertEquals(expectedEmployeeMap, departmentService.findAll(1));
    }

    @Description("Check service correct `findAll` processing")
    @Test
    public void testCorrectFindAll() {
        Map<Integer, List<Employee>> expectedEmployeeMap = new HashMap<>();

        List<Employee> employeeListFirst = getEmployeeList(1);
        expectedEmployeeMap.put(1, employeeListFirst);

        List<Employee> employeeListSecond = getEmployeeList(2);
        expectedEmployeeMap.put(2, employeeListSecond);

        ArrayList<Employee> employeeList = (ArrayList<Employee>) ListUtils.union(employeeListFirst, employeeListSecond);

        when(employeeServiceMock.findAll()).thenReturn(employeeList);

        assertEquals(expectedEmployeeMap, departmentService.findAll());
    }

    private List<Employee> getEmployeeList(int departmentId) {
        List<Employee> result = new ArrayList<>();
        Name name = (new Faker()).name();

        for (int counter = 0; counter < 5; counter++) {
            result.add(new Employee(name.firstName(), name.lastName(), departmentId, 1000 + (counter * 100)));
        }

        return result;
    }
}
