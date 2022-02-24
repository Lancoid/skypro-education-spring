package com.company.skyproeducationspring;

import com.company.skyproeducationspring.utils.BaseManualTestClass;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@DisplayName("Employee Controller Test")
@Epic("EmployeeService")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeControllerTest extends BaseManualTestClass {
    public static Object[][] correctProvider() {
        return new Object[][]{
                {"Иван", "Иванов"},
                {"Ivan", "Ivanov"},
                {"22аа", "333ббб"},
        };
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------------- */

    @Description("Check service correct `plus` calculations")
    @ParameterizedTest
    @MethodSource("correctProvider")
    @Order(1)
    public void testCorrectAddFindRemove(String firstName, String lastName) {
        WebElement body;
        WebElement div;

        /* -- Успешно добавляем сотрудника -- */

        driver.get("http://localhost:8888/employee/add?firstName=" + firstName + "&lastName=" + lastName);

        body = driver.findElement(By.cssSelector("body"));

        Assertions.assertTrue(body.isDisplayed());
        Assertions.assertEquals("Сотрудник добавлен : Employee {firstName='" + firstName + "', lastName='" + lastName + "'}", body.getText());

        /* -- Успешно находим сотрудника -- */

        driver.get("http://localhost:8888/employee/find?firstName=" + firstName + "&lastName=" + lastName);

        body = driver.findElement(By.cssSelector("body"));

        Assertions.assertTrue(body.isDisplayed());
        Assertions.assertEquals("Сотрудник найден : Employee {firstName='" + firstName + "', lastName='" + lastName + "'}", body.getText());

        /* -- Успешно удаляем сотрудника -- */

        driver.get("http://localhost:8888/employee/remove?firstName=" + firstName + "&lastName=" + lastName);

        body = driver.findElement(By.cssSelector("body"));

        Assertions.assertTrue(body.isDisplayed());
        Assertions.assertEquals("Сотрудник удалён : Employee {firstName='" + firstName + "', lastName='" + lastName + "'}", body.getText());

        /* -- Не находим сотрудника -- */

        driver.get("http://localhost:8888/employee/find?firstName=" + firstName + "&lastName=" + lastName);

        div = driver.findElement(By.xpath("//body/div[2]"));

        Assertions.assertTrue(div.isDisplayed());
        Assertions.assertEquals("There was an unexpected error (type=Not Found, status=404).", div.getText());
    }

    @Description("Check service correct `add` , `find` and `remove` processing")
    @ParameterizedTest
    @MethodSource("correctProvider")
    @Order(2)
    public void testEmployeeAlreadyAddedException(String firstName, String lastName) {
        WebElement body;
        WebElement div;

        /* -- Успешно добавляем сотрудника -- */

        driver.get("http://localhost:8888/employee/add?firstName=" + firstName + "&lastName=" + lastName);

        body = driver.findElement(By.cssSelector("body"));

        Assertions.assertTrue(body.isDisplayed());
        Assertions.assertEquals("Сотрудник добавлен : Employee {firstName='" + firstName + "', lastName='" + lastName + "'}", body.getText());

        /* -- Повторно добавляем сотрудника -- */

        driver.get("http://localhost:8888/employee/add?firstName=" + firstName + "&lastName=" + lastName);

        div = driver.findElement(By.xpath("//body/div[2]"));

        Assertions.assertTrue(div.isDisplayed());
        Assertions.assertEquals("There was an unexpected error (type=Bad Request, status=400).", div.getText());

        /* -- Успешно удаляем сотрудника -- */

        driver.get("http://localhost:8888/employee/remove?firstName=" + firstName + "&lastName=" + lastName);

        body = driver.findElement(By.cssSelector("body"));

        Assertions.assertTrue(body.isDisplayed());
        Assertions.assertEquals("Сотрудник удалён : Employee {firstName='" + firstName + "', lastName='" + lastName + "'}", body.getText());
    }

    @Test
    @Description("Check service correct `add` , `find` and `remove` processing")
    @Order(3)
    public void testEmployeeNotAddedException() {
        WebElement body;
        WebElement div;
        Name name = (new Faker()).name();
        String firstName;
        String lastName;

        for (int i = 0; i <= 5; i++) {
            firstName = name.firstName();
            lastName = name.lastName();

            driver.get("http://localhost:8888/employee/add?firstName=" + firstName + "&lastName=" + lastName);


            if (i == 5) {
                div = driver.findElement(By.xpath("//body/div[2]"));

                Assertions.assertTrue(div.isDisplayed());
                Assertions.assertEquals("There was an unexpected error (type=Internal Server Error, status=500).", div.getText());
            } else {
                body = driver.findElement(By.cssSelector("body"));

                Assertions.assertTrue(body.isDisplayed());
                Assertions.assertEquals("Сотрудник добавлен : Employee {firstName='" + firstName + "', lastName='" + lastName + "'}", body.getText());
            }
        }
    }
}