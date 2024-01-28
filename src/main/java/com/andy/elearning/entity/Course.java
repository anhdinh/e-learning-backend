package com.andy.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    private String videoUrl;
    private Boolean available;
    private LocalDateTime createDate;
    @OneToMany
    private Set<Module> modules;
    @OneToMany
    private Set<Quiz> quizzes;
    @OneToMany
    private Set<Enrolment> enrolments;

    private Boolean progressLimited;
}
