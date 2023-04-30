package com.holeyko.naumentask.domen;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "people",
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Person {
    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @Size(min = 2, max = 64)
    private String name;

    @Min(0)
    private int age;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, optional = false)
    private PersonStatistics statistics;
}
