package com.andy.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class QuizAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private QuizQuestion quizQuestion;
    private String answerText;
    private Boolean correct;

}
