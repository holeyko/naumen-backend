package com.holeyko.naumentask.controller;

import com.holeyko.naumentask.domen.Person;
import com.holeyko.naumentask.service.PeopleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private static final int POSITIVE_NUMBER = 1;
    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public void uploadPeople(@RequestParam("peopleFile") MultipartFile peopleFile) {
        peopleService.uploadPeople(peopleFile);
    }

    @GetMapping("/age")
    public Integer getPersonAge(@RequestParam("personName") String personName) {
        Optional<Person> optPerson = peopleService.findByName(personName);
        if (optPerson.isPresent()) {
            Person person = peopleService.incrementCountRequest(optPerson.orElseThrow());
            return person.getAge();
        }

        return POSITIVE_NUMBER;
    }

    @GetMapping("/oldest")
    public List<Person> oldestPeople() {
        return peopleService.findPeopleWithMaxAge();
    }
}
