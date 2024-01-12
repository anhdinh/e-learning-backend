package com.andy.elearning.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserLesson {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Lesson lesson;
    @ManyToOne
    private User user;

    private LocalDateTime completeDateTime;


}
