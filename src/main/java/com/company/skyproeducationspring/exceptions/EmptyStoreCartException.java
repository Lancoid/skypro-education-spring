package com.company.skyproeducationspring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyStoreCartException extends RuntimeException {
    public EmptyStoreCartException() {
        super();
    }

    public EmptyStoreCartException(String message) {
        super(message);
    }

    public EmptyStoreCartException(String message, Throwable t) {
        super(message, t);
    }

    public EmptyStoreCartException(Throwable t) {
        super(t);
    }
}
