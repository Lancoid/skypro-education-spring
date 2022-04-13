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

@DisplayName("Calculator Controller Test")
@Epic("CalculatorService")
public class CalculatorControllerTest extends BaseManualTestClass {
    public static Object[][] plusDataProvider() {
        return new Object[][]{
                {-101, -101, "-101 + (-101) = -202"},
                {2, 3, "2 + 3 = 5"},
                {11, 22, "11 + 22 = 33"}
        };
    }

    public static Object[][] minusDataProvider() {
        return new Object[][]{
                {-101, -101, "-101 - (-101) = 0"},
                {2, 3, "2 - 3 = -1"},
                {33, 11, "33 - 11 = 22"}
        };
    }

    public static Object[][] multiplyDataProvider() {
        return new Object[][]{
                {-101, 2, "-101 * 2 = -202"},
                {2, 3, "2 * 3 = 6"},
                {101, 101, "101 * 101 = 10201"}
        };
    }

    public static Object[][] divideDataProvider() {
        return new Object[][]{
                {-101, -101, "-101 / (-101) = 1"},
                {4, 2, "4 / 2 = 2"},
                {122, 11, "122 / 11 = 11"}
        };
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------------- */

    @Description("Check controller correct `plus` calculations")
    @ParameterizedTest
    @MethodSource("plusDataProvider")
    public void plus(int a, int b, String expected) {
        driver.get("http://localhost:8888/calculator/plus?a=" + a + "&b=" + b);

        WebElement h1 = driver.findElement(By.cssSelector("div.starter-template > h1:nth-child(1)"));

        Assertions.assertTrue(h1.isDisplayed());
        Assertions.assertEquals("Сложение в калькуляторе", h1.getText());

        WebElement code = driver.findElement(By.cssSelector("div.starter-template > code:nth-child(2)"));

        Assertions.assertTrue(code.isDisplayed());
        Assertions.assertEquals(expected, code.getText());
    }

    @Description("Check controller correct `minus` calculations")
    @ParameterizedTest
    @MethodSource("minusDataProvider")
    public void minus(int a, int b, String expected) {
        driver.get("http://localhost:8888/calculator/minus?a=" + a + "&b=" + b);

        WebElement h1 = driver.findElement(By.cssSelector("div.starter-template > h1:nth-child(1)"));

        Assertions.assertTrue(h1.isDisplayed());
        Assertions.assertEquals("Вычитание в калькуляторе", h1.getText());

        WebElement code = driver.findElement(By.cssSelector("div.starter-template > code:nth-child(2)"));

        Assertions.assertTrue(code.isDisplayed());
        Assertions.assertEquals(expected, code.getText());
    }

    @Description("Check controller correct `multiply` calculations")
    @ParameterizedTest
    @MethodSource("multiplyDataProvider")
    public void multiply(int a, int b, String expected) {
        driver.get("http://localhost:8888/calculator/multiply?a=" + a + "&b=" + b);

        WebElement h1 = driver.findElement(By.cssSelector("div.starter-template > h1:nth-child(1)"));

        Assertions.assertTrue(h1.isDisplayed());
        Assertions.assertEquals("Умножение в калькуляторе", h1.getText());

        WebElement code = driver.findElement(By.cssSelector("div.starter-template > code:nth-child(2)"));

        Assertions.assertTrue(code.isDisplayed());
        Assertions.assertEquals(expected, code.getText());
    }

    @Description("Check controller correct `divide` calculations")
    @ParameterizedTest
    @MethodSource("divideDataProvider")
    public void divide(int a, int b, String expected) {
        driver.get("http://localhost:8888/calculator/divide?a=" + a + "&b=" + b);

        WebElement h1 = driver.findElement(By.cssSelector("div.starter-template > h1:nth-child(1)"));

        Assertions.assertTrue(h1.isDisplayed());
        Assertions.assertEquals("Деление в калькуляторе", h1.getText());

        WebElement element = driver.findElement(By.cssSelector("div.starter-template > code:nth-child(2)"));

        Assertions.assertTrue(element.isDisplayed());
        Assertions.assertEquals(expected, element.getText());
    }

    @Description("Check controller correct `divide` calculations by zero")
    @Test
    public void divideByZero() {
        driver.get("http://localhost:8888/calculator/divide?a=11&b=0");

        WebElement element = driver.findElement(By.cssSelector("td#error-message"));

        Assertions.assertTrue(element.isDisplayed());
        Assertions.assertEquals("Нельзя делить на ноль", element.getText());
    }
}
