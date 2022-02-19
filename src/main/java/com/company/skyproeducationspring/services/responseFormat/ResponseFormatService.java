package com.company.skyproeducationspring.services.responseFormat;

import org.springframework.stereotype.Service;

@Service
public class ResponseFormatService implements ResponseFormatServiceInterface {
    @Override
    public String calculatorResponse(int a, int b, char symbol, int result) {
        return a + " " + symbol + " " +
                (b < 0 ? "(" : "") + b + (b < 0 ? ")" : "") +
                " = " + result;
    }
}
