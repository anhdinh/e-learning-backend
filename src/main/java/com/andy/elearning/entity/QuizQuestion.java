package com.andy.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class QuizQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Quiz quiz;

    private String questionTitle;
    @Basic
    private Boolean passRequired;

    public Boolean getPassRequired() {
        return passRequired;
    }

    public void setPassRequired(Boolean passRequired) {
        this.passRequired = passRequired;
    }
}
