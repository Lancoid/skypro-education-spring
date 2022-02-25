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

@DisplayName("Employee Service Test")
@Epic("EmployeeService")
public class EmployeeServiceUnitTest {
    public static Object[][] correctProvider() {
        return new Object[][]{
                {"Иван", "Иванов"},
                {"Ivan", "Ivanov"},
                {"22аа", "333ббб"},
        };
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------------- */

    @Description("Check service correct `add` , `find` and `remove` processing")
    @ParameterizedTest
    @MethodSource("correctProvider")
    public void testCorrectAddFindRemove(String firstName, String lastName) {
        EmployeeServiceInterface service = new EmployeeService();

        /* -- Успешно добавляем сотрудника -- */

        Employee addedEmployee = service.add(firstName, lastName);

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
    public void testEmployeeAlreadyAddedException(String firstName, String lastName) {
        EmployeeServiceInterface service = new EmployeeService();

        /* -- Успешно добавляем сотрудника -- */

        service.add(firstName, lastName);

        /* -- Повторно добавляем сотрудника -- */

        Exception exception = Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> service.add(firstName, lastName));

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
        Faker faker = new Faker();
        Name name = faker.name();
        ArrayList<Employee> resultArray = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            resultArray.add(service.add(name.firstName(), name.lastName()));
        }

        Assertions.assertEquals(resultArray, service.findAll());
    }
}
