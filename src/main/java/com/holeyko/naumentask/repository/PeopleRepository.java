package com.holeyko.naumentask.repository;

import com.holeyko.naumentask.domen.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String name);

    @Query(value = "SELECT * FROM people WHERE age=(SELECT MAX(age) FROM people)", nativeQuery = true)
    List<Person> findPeopleWithMaxAge();
}
