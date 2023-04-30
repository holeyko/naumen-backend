package com.holeyko.naumentask.domen;

import com.holeyko.naumentask.service.PeopleService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "people_statistics")
public class PersonStatistics {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Person person;

    private int countRequest = 0;

    public void incrementCountRequest() {
        ++countRequest;
    }
}
