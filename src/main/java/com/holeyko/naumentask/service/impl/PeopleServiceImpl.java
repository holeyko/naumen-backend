package com.holeyko.naumentask.service.impl;

import com.holeyko.naumentask.domen.Person;
import com.holeyko.naumentask.domen.PersonStatistics;
import com.holeyko.naumentask.exception.PeopleParseException;
import com.holeyko.naumentask.exception.PersonSyntaxException;
import com.holeyko.naumentask.repository.PeopleRepository;
import com.holeyko.naumentask.service.PeopleService;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeopleServiceImpl implements PeopleService {
    private final PeopleRepository peopleRepository;
    private final Validator validator;

    public PeopleServiceImpl(PeopleRepository peopleRepository, Validator validator) {
        this.peopleRepository = peopleRepository;
        this.validator = validator;
    }

    @Override
    @Transactional
    public Person save(Person person) {
        var personStatistics = new PersonStatistics();
        person.setStatistics(personStatistics);
        personStatistics.setPerson(person);
        return peopleRepository.save(person);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findById(long id) {
        return peopleRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findByName(String name) {
        return peopleRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    @Override
    @Transactional
    public Person incrementCountRequest(Person person) {
        person.getStatistics().incrementCountRequest();
        return save(person);
    }

    @Override
    public List<Person> findPeopleWithMaxAge() {
        return peopleRepository.findPeopleWithMaxAge();
    }

    @Override
    public void uploadPeople(MultipartFile peopleFile) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(peopleFile.getInputStream(), StandardCharsets.UTF_8))
        ) {
            List<String> incorrectPeople = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                try {
                    save(parsePerson(line));
                } catch (PersonSyntaxException e) {
                    incorrectPeople.add(line);
                }
            }

            if (!incorrectPeople.isEmpty()) {
                throw new PeopleParseException(incorrectPeople);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private Person parsePerson(String personStr) {
        String[] splitParameters = personStr.split("_");

        if (splitParameters.length != 2) {
            throw new PersonSyntaxException();
        }

        String name = splitParameters[0];
        int age;
        try {
            age = Integer.parseInt(splitParameters[1]);
        } catch (NumberFormatException e) {
            throw new PersonSyntaxException();
        }

        Person person = new Person();
        person.setName(name);
        person.setAge(age);

        if (!validator.validate(person).isEmpty()) {
            throw new PersonSyntaxException();
        }

        return person;
    }
}
