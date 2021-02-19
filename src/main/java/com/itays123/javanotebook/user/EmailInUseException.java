package com.itays123.javanotebook.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Email is already in use")
public class EmailInUseException extends RuntimeException {
    public EmailInUseException() {
    }
    public EmailInUseException(String message) {
        super(message);
    }
}
