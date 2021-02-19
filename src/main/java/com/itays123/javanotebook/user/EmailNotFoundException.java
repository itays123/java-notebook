package com.itays123.javanotebook.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Could not find user with email")
public class EmailNotFoundException extends RuntimeException {

    public EmailNotFoundException() {
    }

    public EmailNotFoundException(String message) {
        super(message);
    }
}
