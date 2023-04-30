package com.holeyko.naumentask.service;

import com.holeyko.naumentask.domen.Person;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PeopleService {
    Person save(Person person);

    Optional<Person> findById(long id);

    Optional<Person> findByName(String name);

    List<Person> findPeopleWithMaxAge();

    List<Person> findAll();

    Person incrementCountRequest(Person person);

    void uploadPeople(MultipartFile peopleFile);
}
