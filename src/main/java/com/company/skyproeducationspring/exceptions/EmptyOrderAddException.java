package com.company.skyproeducationspring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyOrderAddException extends RuntimeException {
    public EmptyOrderAddException() {
        super();
    }

    public EmptyOrderAddException(String message) {
        super(message);
    }

    public EmptyOrderAddException(String message, Throwable t) {
        super(message, t);
    }

    public EmptyOrderAddException(Throwable t) {
        super(t);
    }
}
