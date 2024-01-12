package com.andy.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer number;

    @ManyToOne
    private Module module;

    private String videoUrl;

    private String lessonDetail;
    private Integer courseOrder;

}
