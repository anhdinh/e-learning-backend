package com.andy.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    Course course;

    private String name;

    private Integer number;
    @OneToMany
    private Set<Lesson> lessons;


}
