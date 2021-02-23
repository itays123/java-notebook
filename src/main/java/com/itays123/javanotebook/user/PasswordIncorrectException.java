package com.itays123.javanotebook.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Incorrect Password")
public class PasswordIncorrectException extends RuntimeException {
    public PasswordIncorrectException() {
    }
    public PasswordIncorrectException(String message) {
        super(message);
    }
}
