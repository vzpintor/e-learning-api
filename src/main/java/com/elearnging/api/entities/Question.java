package com.elearnging.api.entities;

import com.elearnging.api.enums.QuestionTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private double score;

    @Enumerated(EnumType.STRING)
    private QuestionTypeEnum type;

    @ManyToOne
    private Lesson lesson;

    @ElementCollection
    private List<String> options;

    private String correctAnswer;

    @ElementCollection
    private List<String> correctAnswers;
}
