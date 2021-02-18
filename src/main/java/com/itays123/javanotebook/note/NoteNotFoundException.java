package com.itays123.javanotebook.note;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "could not find note")
public class NoteNotFoundException extends RuntimeException{
}
