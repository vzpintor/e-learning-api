package com.elearnging.api.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private double approvalThreshold;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "lesson")
    private List<Question> questions;

    @ManyToOne
    private Lesson previousLesson;
}
