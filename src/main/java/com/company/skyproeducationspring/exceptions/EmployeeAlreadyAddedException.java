package com.company.skyproeducationspring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeAlreadyAddedException extends RuntimeException {
    public EmployeeAlreadyAddedException() {
        super();
    }

    public EmployeeAlreadyAddedException(String message) {
        super(message);
    }

    public EmployeeAlreadyAddedException(String message, Throwable t) {
        super(message, t);
    }

    public EmployeeAlreadyAddedException(Throwable t) {
        super(t);
    }
}
