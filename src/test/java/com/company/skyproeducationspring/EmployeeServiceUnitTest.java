package com.company.skyproeducationspring;

import com.company.skyproeducationspring.exceptions.EmployeeAlreadyAddedException;
import com.company.skyproeducationspring.exceptions.EmployeeNotFoundException;
import com.company.skyproeducationspring.models.Employee;
import com.company.skyproeducationspring.services.employee.EmployeeService;
import com.company.skyproeducationspring.services.employee.EmployeeServiceInterface;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;

@DisplayName("Employee Service Test")
@Epic("EmployeeService")
public class EmployeeServiceUnitTest {
    public static Object[][] correctProvider() {
        return new Object[][]{
                {"Иван", "Иванов", 1, 10000},
                {"Ivan", "Ivanov", 2, 20000},
                {"22аа", "333ббб", 3, 30000},
        };
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------------- */

    @Description("Check service correct `add` , `find` and `remove` processing")
    @ParameterizedTest
    @MethodSource("correctProvider")
    public void testCorrectAddFindRemove(String firstName, String lastName, int departmentId, float salary) {
        EmployeeServiceInterface service = new EmployeeService();

        /* -- Успешно добавляем сотрудника -- */

        Employee addedEmployee = service.add(firstName, lastName, departmentId, salary);

        Assertions.assertEquals(firstName, addedEmployee.getFirstName());
        Assertions.assertEquals(lastName, addedEmployee.getLastName());

        /* -- Успешно находим сотрудника -- */

        Employee foundedEmployee = service.findOne(firstName, lastName);

        Assertions.assertEquals(firstName, foundedEmployee.getFirstName());
        Assertions.assertEquals(lastName, foundedEmployee.getLastName());

        /* -- Успешно удаляем сотрудника -- */

        Employee removedEmployee = service.remove(firstName, lastName);

        Assertions.assertEquals(firstName, removedEmployee.getFirstName());
        Assertions.assertEquals(lastName, removedEmployee.getLastName());

        /* -- Не находим сотрудника -- */

        Exception exception = Assertions.assertThrows(EmployeeNotFoundException.class, () -> service.findOne(firstName, lastName));

        String expectedMessage = "Сотрудник '" + firstName + " " + lastName + "' не найден";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Description("Check service correct `add` , `find` and `remove` processing")
    @ParameterizedTest
    @MethodSource("correctProvider")
    public void testEmployeeAlreadyAddedException(String firstName, String lastName, int departmentId, float salary) {
        EmployeeServiceInterface service = new EmployeeService();

        /* -- Успешно добавляем сотрудника -- */

        service.add(firstName, lastName, departmentId, salary);

        /* -- Повторно добавляем сотрудника -- */

        Exception exception = Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> service.add(firstName, lastName, departmentId, salary));

        String expectedMessage = "Сотрудник '" + firstName + " " + lastName + "' уже добавлен";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));

        /* -- Успешно удаляем сотрудника -- */

        Employee removedEmployee = service.remove(firstName, lastName);

        Assertions.assertEquals(firstName, removedEmployee.getFirstName());
        Assertions.assertEquals(lastName, removedEmployee.getLastName());
    }

    @Test
    @Description("Check service correct `findAll` processing")
    public void testFindAll() {
        EmployeeServiceInterface service = new EmployeeService();

        ArrayList<Employee> expectedList = new ArrayList<>();
        ArrayList<Employee> serviceList;

        Faker faker = new Faker();
        Name name = faker.name();

        Employee employee;

        for (int i = 0; i < 10; i++) {
            employee = service.add(
                    name.firstName(),
                    name.lastName(),
                    faker.random().nextInt(1, 5),
                    faker.random().nextInt(10_000, 50_000)
            );

            expectedList.add(employee);
        }

        serviceList = service.findAll();


        expectedList.sort(Comparator.comparing(Employee::getSalary));
        serviceList.sort(Comparator.comparing(Employee::getSalary));

        Assertions.assertEquals(expectedList, serviceList);
    }
}
