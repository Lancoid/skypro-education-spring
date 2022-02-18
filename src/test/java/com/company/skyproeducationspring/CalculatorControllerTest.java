package com.company.skyproeducationspring;

import com.company.skyproeducationspring.utils.BaseManualTestClass;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@DisplayName("Calculator Controller Test")
@Epic("CalculatorService")
public class CalculatorControllerTest extends BaseManualTestClass {
    public static Object[][] plusDataProvider() {
        return new Object[][]{
                {-101, -101, "-202"},
                {2, 3, "5"},
                {11, 22, "33"}
        };
    }

    public static Object[][] minusDataProvider() {
        return new Object[][]{
                {-101, -101, "0"},
                {2, 3, "-1"},
                {33, 11, "22"}
        };
    }

    public static Object[][] multiplyDataProvider() {
        return new Object[][]{
                {-101, 2, "-202"},
                {2, 3, "6"},
                {101, 101, "10201"}
        };
    }

    public static Object[][] divideDataProvider() {
        return new Object[][]{
                {-101, 0, "Нельзя делить на ноль"},
                {4, 2, "2"},
                {122, 11, "11"}
        };
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------------- */

    @Description("Check service correct `plus` calculations")
    @ParameterizedTest
    @MethodSource("plusDataProvider")
    public void plus(int a, int b, String expected) {
        driver.get("http://localhost:8888/calculator/plus?a=" + a + "&b=" + b);

        WebElement body = driver.findElement(By.cssSelector("body"));

        Assertions.assertTrue(body.isDisplayed());
        Assertions.assertEquals(expected, body.getText());
    }

    @Description("Check service correct `minus` calculations")
    @ParameterizedTest
    @MethodSource("minusDataProvider")
    public void minus(int a, int b, String expected) {
        driver.get("http://localhost:8888/calculator/minus?a=" + a + "&b=" + b);

        WebElement body = driver.findElement(By.cssSelector("body"));

        Assertions.assertTrue(body.isDisplayed());
        Assertions.assertEquals(expected, body.getText());
    }

    @Description("Check service correct `multiply` calculations")
    @ParameterizedTest
    @MethodSource("multiplyDataProvider")
    public void multiply(int a, int b, String expected) {
        driver.get("http://localhost:8888/calculator/multiply?a=" + a + "&b=" + b);

        WebElement body = driver.findElement(By.cssSelector("body"));

        Assertions.assertTrue(body.isDisplayed());
        Assertions.assertEquals(expected, body.getText());
    }

    @Description("Check service correct `divide` calculations")
    @ParameterizedTest
    @MethodSource("divideDataProvider")
    public void divide(int a, int b, String expected) {
        driver.get("http://localhost:8888/calculator/divide?a=" + a + "&b=" + b);

        WebElement body = driver.findElement(By.cssSelector("body"));

        Assertions.assertTrue(body.isDisplayed());
        Assertions.assertEquals(expected, body.getText());
    }
}
