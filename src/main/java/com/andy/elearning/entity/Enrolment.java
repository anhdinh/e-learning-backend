package com.andy.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "enrolment")
public class Enrolment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime completedDateTime;

    private Integer rating;
    private LocalDateTime enrollTime;
    private Boolean canceled;
    private LocalDateTime expiredTime;

}
