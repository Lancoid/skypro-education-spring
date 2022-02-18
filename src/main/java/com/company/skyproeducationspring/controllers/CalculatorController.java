package com.company.skyproeducationspring.controllers;

import com.company.skyproeducationspring.services.calculator.CalculatorServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    private final CalculatorServiceInterface service;

    public CalculatorController(CalculatorServiceInterface service) {
        this.service = service;
    }

    @GetMapping(path = "/calculator/plus")
    public String plus(@RequestParam(name = "a") int a, @RequestParam(name = "b") int b) {
        return String.valueOf(service.plus(a, b));
    }

    @GetMapping(path = "/calculator/minus")
    public String minus(@RequestParam(name = "a") int a, @RequestParam(name = "b") int b) {
        return String.valueOf(service.minus(a, b));
    }

    @GetMapping(path = "/calculator/multiply")
    public String multiply(@RequestParam(name = "a") int a, @RequestParam(name = "b") int b) {
        return String.valueOf(service.multiply(a, b));
    }

    @GetMapping(path = "/calculator/divide")
    public String divide(@RequestParam(name = "a") int a, @RequestParam(name = "b") int b) {
        try {
            return String.valueOf(service.divide(a, b));
        } catch (Throwable throwable) {
            return throwable.getMessage();
        }
    }
}
