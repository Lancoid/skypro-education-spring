package com.company.skyproeducationspring;

import com.company.skyproeducationspring.utils.BaseManualTestClass;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@DisplayName("Hello Controller Test")
@Epic("Hello service")
public class HelloControllerTest extends BaseManualTestClass {
    @Test
    @Description("Check controller correct processing without `username` get-parameter")
    public void correctWithoutUsername() {
        driver.get("http://localhost:8888/");

        WebElement body = driver.findElement(By.cssSelector("body"));

        Assertions.assertTrue(body.isDisplayed());
        Assertions.assertEquals("Hello", body.getText());
    }

    @ParameterizedTest
    @MethodSource("correctWithUsernameDataProvider")
    @Description("Check controller correct processing with `username` get-parameter")
    public void correctWithUsername(String username, String expected) {
        driver.get("http://localhost:8888/hello?name=" + username);

        WebElement body = driver.findElement(By.cssSelector("body"));

        Assertions.assertTrue(body.isDisplayed());
        Assertions.assertEquals(expected, body.getText());
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------------- */

    public static Object[][] correctWithUsernameDataProvider() {
        return new Object[][]{
                {"username", "Hello, username"},
                {"Vasya", "Hello, Vasya"},
                {"232324TbiDbiSh", "Hello, 232324TbiDbiSh"}
        };
    }
}
