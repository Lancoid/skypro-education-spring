package com.company.skyproeducationspring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeNotAddedException extends RuntimeException {
    public EmployeeNotAddedException() {
        super();
    }

    public EmployeeNotAddedException(String message) {
        super(message);
    }

    public EmployeeNotAddedException(String message, Throwable t) {
        super(message, t);
    }

    public EmployeeNotAddedException(Throwable t) {
        super(t);
    }
}
