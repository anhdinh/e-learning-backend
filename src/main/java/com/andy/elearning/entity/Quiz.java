package com.andy.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    Course course;

    private String name;

    private String number;

    private Integer courseOrder;

    private Integer minPassScore;

   private Boolean passRequired;
}
