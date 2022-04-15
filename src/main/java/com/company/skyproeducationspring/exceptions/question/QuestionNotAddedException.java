package com.company.skyproeducationspring.exceptions.question;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class QuestionNotAddedException extends RuntimeException {
    public QuestionNotAddedException() {
        super();
    }

    public QuestionNotAddedException(String message) {
        super(message);
    }

    public QuestionNotAddedException(String message, Throwable t) {
        super(message, t);
    }

    public QuestionNotAddedException(Throwable t) {
        super(t);
    }
}
