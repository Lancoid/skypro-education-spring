package com.company.skyproeducationspring.exceptions.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeValidatorException extends RuntimeException {
    public EmployeeValidatorException() {
        super();
    }

    public EmployeeValidatorException(String message) {
        super(message);
    }

    public EmployeeValidatorException(String message, Throwable t) {
        super(message, t);
    }

    public EmployeeValidatorException(Throwable t) {
        super(t);
    }
}
