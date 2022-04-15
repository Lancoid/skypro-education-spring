package com.company.skyproeducationspring.exceptions.question;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class QuestionNotRemoveException extends RuntimeException {
    public QuestionNotRemoveException() {
        super();
    }

    public QuestionNotRemoveException(String message) {
        super(message);
    }

    public QuestionNotRemoveException(String message, Throwable t) {
        super(message, t);
    }

    public QuestionNotRemoveException(Throwable t) {
        super(t);
    }
}
