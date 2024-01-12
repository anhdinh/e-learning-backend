package com.andy.elearning.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class QuizQuestion {
    @Id
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
