package com.holeyko.naumentask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("*")
public class NotFoundController {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound() {}
}
