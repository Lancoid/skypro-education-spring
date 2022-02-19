package com.company.skyproeducationspring.services.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService implements CalculatorServiceInterface {

    @Override
    public int plus(final int num1, final int num2) {
        return num1 + num2;
    }

    @Override
    public int minus(final int num1, final int num2) {
        return num1 - num2;
    }

    @Override
    public int multiply(final int num1, final int num2) {
        return num1 * num2;
    }

    @Override
    public int divide(final int num1, final int num2) {
        if (num2 == 0) {
            throw new RuntimeException("Нельзя делить на ноль");
        }
        return num1 / num2;
    }
}
