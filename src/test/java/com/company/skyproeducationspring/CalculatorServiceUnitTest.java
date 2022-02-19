package com.company.skyproeducationspring;

import com.company.skyproeducationspring.services.calculator.CalculatorService;
import com.company.skyproeducationspring.services.calculator.CalculatorServiceInterface;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Calculator Service Test")
@Epic("CalculatorService")
public class CalculatorServiceUnitTest {
    public static Object[][] plusDataProvider() {
        return new Object[][]{
                {-101, -101, -202},
                {2, 3, 5},
                {11, 22, 33}
        };
    }

    public static Object[][] minusDataProvider() {
        return new Object[][]{
                {-101, -101, 0},
                {2, 3, -1},
                {33, 11, 22}
        };
    }

    public static Object[][] multiplyDataProvider() {
        return new Object[][]{
                {-101, 2, -202},
                {2, 3, 6},
                {101, 101, 10201}
        };
    }

    public static Object[][] divideDataProvider() {
        return new Object[][]{
                {-101, 0, 0},
                {4, 2, 2},
                {122, 11, 11}
        };
    }

    /* -------------------------------------------------------------------------------------------------------------------------------------------- */

    @Description("Check service correct `plus` calculations")
    @ParameterizedTest
    @MethodSource("plusDataProvider")
    public void plus(int a, int b, int expected) {
        CalculatorServiceInterface service = new CalculatorService();
        Assertions.assertEquals(expected, service.plus(a, b));
    }

    @Description("Check service correct `minus` calculations")
    @ParameterizedTest
    @MethodSource("minusDataProvider")
    public void minus(int a, int b, int expected) {
        CalculatorServiceInterface service = new CalculatorService();
        Assertions.assertEquals(expected, service.minus(a, b));
    }

    @Description("Check service correct `multiply` calculations")
    @ParameterizedTest
    @MethodSource("multiplyDataProvider")
    public void multiply(int a, int b, int expected) {
        CalculatorServiceInterface service = new CalculatorService();
        Assertions.assertEquals(expected, service.multiply(a, b));
    }

    @Description("Check service correct `divide` calculations")
    @ParameterizedTest
    @MethodSource("divideDataProvider")
    public void divide(int a, int b, int expected) {
        CalculatorServiceInterface service = new CalculatorService();

        if (b != 0) {
            Assertions.assertEquals(expected, service.divide(a, b));
        } else {
            Exception exception = Assertions.assertThrows(RuntimeException.class, () -> service.divide(a, b));

            String expectedMessage = "Нельзя делить на ноль";
            String actualMessage = exception.getMessage();

            Assertions.assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}
