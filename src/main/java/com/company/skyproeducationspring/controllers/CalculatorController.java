package com.company.skyproeducationspring.controllers;

import com.company.skyproeducationspring.services.calculator.CalculatorServiceInterface;
import com.company.skyproeducationspring.services.responseFormat.ResponseFormatServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calculator")
public class CalculatorController {
    private final CalculatorServiceInterface calculatorService;
    private final ResponseFormatServiceInterface responseFormatService;

    public CalculatorController(CalculatorServiceInterface calculatorService, ResponseFormatServiceInterface responseFormatService) {
        this.calculatorService = calculatorService;
        this.responseFormatService = responseFormatService;
    }

    @GetMapping(path = "/plus")
    public String plus(int a, int b, Model model) {
        model.addAttribute("text", responseFormatService.calculatorResponse(a, b, '+', calculatorService.plus(a, b)));

        return "calculator/plus";
    }

    @GetMapping(path = "/minus")
    public String minus(int a, int b, Model model) {
        model.addAttribute("text", responseFormatService.calculatorResponse(a, b, '-', calculatorService.minus(a, b)));

        return "calculator/minus";
    }

    @GetMapping(path = "/multiply")
    public String multiply(int a, int b, Model model) {
        model.addAttribute("text", responseFormatService.calculatorResponse(a, b, '*', calculatorService.multiply(a, b)));

        return "calculator/multiply";
    }

    @GetMapping(path = "/divide")
    public String divide(int a, int b, Model model) {
        model.addAttribute("text", responseFormatService.calculatorResponse(a, b, '/', calculatorService.divide(a, b)));

        return "calculator/divide";
    }
}
