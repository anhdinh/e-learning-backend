package com.andy.elearning.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class QuizAnswer {
    @Id
    private Long id;

    @ManyToOne
    private QuizQuestion quizQuestion;
    private String answerText;
    private Boolean correct;

}
