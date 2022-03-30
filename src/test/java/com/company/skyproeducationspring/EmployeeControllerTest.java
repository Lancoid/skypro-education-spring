package com.company.skyproeducationspring;

import com.company.skyproeducationspring.models.Employee;
import com.company.skyproeducationspring.utils.BaseManualTestClass;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Locale;

@DisplayName("Employee Controller Test")
@Epic("EmployeeService")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeControllerTest extends BaseManualTestClass {
    public static Object[][] correctProvider() {
        return new Object[][]{
                {"Иван", "Иванов", 1, 1000.11f},
                {"Ivan", "Ivanov", 2, 2000.22f},
                {"ааа", "ббб", 3, 3000.33f},
        };
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------------- */

    @Description("Check controller correct processing `add`/`find`/`remove` actions")
    @ParameterizedTest
    @MethodSource("correctProvider")
    @Order(1)
    public void testCorrectAddFindRemove(String firstName, String lastName, int departmentId, float salary) {
        /* -- Успешно добавляем сотрудника -- */

        driver.get("http://localhost:8888/employee/add?firstName=" + firstName + "&lastName=" + lastName + "&departmentId=" + departmentId + "&salary=" + salary);

        checkPage(firstName, lastName, departmentId, salary, "Сотрудник добавлен");

        /* -- Успешно находим сотрудника -- */

        driver.get("http://localhost:8888/employee/find?firstName=" + firstName + "&lastName=" + lastName);

        checkPage(firstName, lastName, departmentId, salary, "Информация по сотруднику");

        /* -- Успешно удаляем сотрудника -- */

        driver.get("http://localhost:8888/employee/remove?firstName=" + firstName + "&lastName=" + lastName);

        checkPage(firstName, lastName, departmentId, salary, "Сотрудник удалён");

        /* -- Не находим сотрудника -- */

        driver.get("http://localhost:8888/employee/find?firstName=" + firstName + "&lastName=" + lastName);

        checkException(firstName, lastName, "не найден", "404");
    }

    @Description("Check controller correct processing `EmployeeAlreadyAddedException`")
    @ParameterizedTest
    @MethodSource("correctProvider")
    @Order(2)
    public void testEmployeeAlreadyAddedException(String firstName, String lastName, int departmentId, float salary) {
        /* -- Успешно добавляем сотрудника -- */

        driver.get("http://localhost:8888/employee/add?firstName=" + firstName + "&lastName=" + lastName + "&departmentId=" + departmentId + "&salary=" + salary);

        checkPage(firstName, lastName, departmentId, salary, "Сотрудник добавлен");

        /* -- Повторно добавляем сотрудника -- */

        driver.get("http://localhost:8888/employee/add?firstName=" + firstName + "&lastName=" + lastName + "&departmentId=" + departmentId + "&salary=" + salary);

        checkException(firstName, lastName, "уже добавлен", "400");

        /* -- Успешно удаляем сотрудника -- */

        driver.get("http://localhost:8888/employee/remove?firstName=" + firstName + "&lastName=" + lastName);

        checkPage(firstName, lastName, departmentId, salary, "Сотрудник удалён");
    }

    @Test
    @Description("Check controller correct processing `find-all` action")
    @Order(3)
    public void testEmployeeFindAll() {
        Faker faker = new Faker();
        Name name = faker.name();

        String firstName;
        String lastName;
        int departmentId;
        float salary;

        ArrayList<Employee> expectedEmployeeList = new ArrayList<>();


        for (int i = 0; i < 5; i++) {
            firstName = name.firstName();
            lastName = name.lastName();
            departmentId = faker.random().nextInt(1, 5);
            salary = faker.random().nextInt(10_000, 50_000);

            driver.get("http://localhost:8888/employee/add?firstName=" + firstName + "&lastName=" + lastName + "&departmentId=" + departmentId + "&salary=" + salary);

            checkPage(firstName, lastName, departmentId, salary, "Сотрудник добавлен");

            expectedEmployeeList.add(new Employee(firstName, lastName, departmentId, salary));
        }

        driver.get("http://localhost:8888/employee/find-all");

        WebElement h1 = driver.findElement(By.cssSelector("div.starter-template > h1:nth-child(1)"));
        Assertions.assertTrue(h1.isDisplayed());
        Assertions.assertEquals("Список сотрудников", h1.getText());

        for (int i = 1; i <= 5; i++) {
            WebElement tdEmployeeFirstName = driver.findElement(By.cssSelector("tr:nth-child(" + i + ") > td#employee-first-name"));
            Assertions.assertTrue(tdEmployeeFirstName.isDisplayed());
            firstName = tdEmployeeFirstName.getText();

            WebElement tdEmployeeLastName = driver.findElement(By.cssSelector("tr:nth-child(" + i + ") > td#employee-last-name"));
            Assertions.assertTrue(tdEmployeeLastName.isDisplayed());
            lastName = tdEmployeeLastName.getText();

            WebElement tdEmployeeDepartment = driver.findElement(By.cssSelector("tr:nth-child(" + i + ") > td#employee-department"));
            Assertions.assertTrue(tdEmployeeDepartment.isDisplayed());
            departmentId = Integer.parseInt(tdEmployeeDepartment.getText());

            WebElement tdEmployeeSalary = driver.findElement(By.cssSelector("tr:nth-child(" + i + ") > td#employee-salary"));
            Assertions.assertTrue(tdEmployeeSalary.isDisplayed());
            salary = Float.parseFloat(tdEmployeeSalary.getText());

            Assertions.assertTrue(expectedEmployeeList.remove(new Employee(firstName, lastName, departmentId, salary)));
        }

        Assertions.assertEquals(0, expectedEmployeeList.size());
    }

    private void checkPage(String firstName, String lastName, int departmentId, float salary, String string) {
        WebElement h1 = driver.findElement(By.cssSelector("div.starter-template > h1:nth-child(1)"));
        Assertions.assertTrue(h1.isDisplayed());
        Assertions.assertEquals(string, h1.getText());

        WebElement tdEmployeeFirstName = driver.findElement(By.cssSelector("td#employee-first-name"));
        Assertions.assertTrue(tdEmployeeFirstName.isDisplayed());
        Assertions.assertEquals(StringUtils.capitalize(firstName.toLowerCase(Locale.ROOT)), tdEmployeeFirstName.getText());

        WebElement tdEmployeeLastName = driver.findElement(By.cssSelector("td#employee-last-name"));
        Assertions.assertTrue(tdEmployeeLastName.isDisplayed());
        Assertions.assertEquals(StringUtils.capitalize(lastName.toLowerCase(Locale.ROOT)), tdEmployeeLastName.getText());

        WebElement tdEmployeeDepartment = driver.findElement(By.cssSelector("td#employee-department"));
        Assertions.assertTrue(tdEmployeeDepartment.isDisplayed());
        Assertions.assertEquals(departmentId, Integer.parseInt(tdEmployeeDepartment.getText()));

        WebElement tdEmployeeSalary = driver.findElement(By.cssSelector("td#employee-salary"));
        Assertions.assertTrue(tdEmployeeSalary.isDisplayed());
        Assertions.assertEquals(salary, Float.parseFloat(tdEmployeeSalary.getText()));
    }

    private void checkException(String firstName, String lastName, String message, String errorCode) {
        String expectedMessage = "Сотрудник '"
                + StringUtils.capitalize(firstName.toLowerCase(Locale.ROOT)) + " "
                + StringUtils.capitalize(lastName.toLowerCase(Locale.ROOT)) + "' " + message;

        WebElement errorMessage = driver.findElement(By.cssSelector("td#error-message"));

        Assertions.assertTrue(errorMessage.isDisplayed());
        Assertions.assertEquals(expectedMessage, errorMessage.getText());

        WebElement errorCodeSelector = driver.findElement(By.cssSelector("td#error-code"));

        Assertions.assertTrue(errorCodeSelector.isDisplayed());
        Assertions.assertEquals(errorCode, errorCodeSelector.getText());
    }
}
