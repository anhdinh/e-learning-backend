package com.andy.elearning.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Quiz {
    @Id
    private Long id;

    @ManyToOne
    Course course;

    private String name;

    private String number;

    private Integer courseOrder;

    private Integer minPassScore;

   private Boolean passRequired;
}
