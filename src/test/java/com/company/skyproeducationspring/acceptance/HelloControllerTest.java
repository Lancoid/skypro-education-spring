package com.company.skyproeducationspring.acceptance;

import com.company.skyproeducationspring.utils.BaseManualTestClass;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@DisplayName("Hello Controller Test")
@Epic("Hello service")
public class HelloControllerTest extends BaseManualTestClass {
    public static Object[][] correctWithUsernameDataProvider() {
        return new Object[][]{
                {"username", "Hello, username"},
                {"Vasya", "Hello, Vasya"},
                {"232324TbiDbiSh", "Hello, 232324TbiDbiSh"}
        };
    }

    @Test
    @Description("Check controller correct processing without `username` get-parameter")
    public void correctWithoutUsername() {
        driver.get("http://localhost:8888/");

        WebElement h1 = driver.findElement(By.cssSelector("div.starter-template > h1:nth-child(1)"));

        Assertions.assertTrue(h1.isDisplayed());
        Assertions.assertEquals("Hello", h1.getText());
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------------- */

    @ParameterizedTest
    @MethodSource("correctWithUsernameDataProvider")
    @Description("Check controller correct processing with `username` get-parameter")
    public void correctWithUsername(String username, String expected) {
        driver.get("http://localhost:8888/hello?name=" + username);

        WebElement h1 = driver.findElement(By.cssSelector("div.starter-template > h1:nth-child(1)"));

        Assertions.assertTrue(h1.isDisplayed());
        Assertions.assertEquals(expected, h1.getText());
    }
}
