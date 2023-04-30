package com.holeyko.naumentask.controller.handler;

import com.holeyko.naumentask.exception.PeopleParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class PeopleExceptionHandler {
    @ExceptionHandler(PeopleParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, ?> onPeopleParseException(PeopleParseException e) {
        return Map.of("Incorrect people", e.getIncorrectPeople());
    }
}
