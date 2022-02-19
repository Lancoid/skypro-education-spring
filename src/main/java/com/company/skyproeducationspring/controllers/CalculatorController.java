package com.company.skyproeducationspring.controllers;

import com.company.skyproeducationspring.services.calculator.CalculatorServiceInterface;
import com.company.skyproeducationspring.services.responseFormat.ResponseFormatServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    private final CalculatorServiceInterface calculatorService;
    private final ResponseFormatServiceInterface responseFormatService;

    public CalculatorController(CalculatorServiceInterface calculatorService, ResponseFormatServiceInterface responseFormatService) {
        this.calculatorService = calculatorService;
        this.responseFormatService = responseFormatService;
    }

    @GetMapping(path = "/plus")
    public String plus(int a, int b) {
        return responseFormatService.calculatorResponse(a, b, '+', calculatorService.plus(a, b));
    }

    @GetMapping(path = "/minus")
    public String minus(int a, int b) {
        return responseFormatService.calculatorResponse(a, b, '-', calculatorService.minus(a, b));
    }

    @GetMapping(path = "/multiply")
    public String multiply(int a, int b) {
        return responseFormatService.calculatorResponse(a, b, '*', calculatorService.multiply(a, b));
    }

    @GetMapping(path = "/divide")
    public String divide(int a, int b) {
        try {
            return responseFormatService.calculatorResponse(a, b, '/', calculatorService.divide(a, b));
        } catch (Throwable throwable) {
            return throwable.getMessage();
        }
    }
}
