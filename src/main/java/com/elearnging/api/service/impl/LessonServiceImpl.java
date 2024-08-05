package com.elearnging.api.service.impl;

import com.elearnging.api.entities.Lesson;
import com.elearnging.api.entities.Question;
import com.elearnging.api.exceptions.ResourceNotFoundException;
import com.elearnging.api.repository.LessonRepository;
import com.elearnging.api.repository.QuestionRepository;
import com.elearnging.api.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
    }

    @Override
    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson updateLesson(Long id, Lesson lessonDetails) {
        Lesson lesson = getLessonById(id);
        lesson.setTitle(lessonDetails.getTitle());
        lesson.setDescription(lessonDetails.getDescription());
        lesson.setApprovalThreshold(lessonDetails.getApprovalThreshold());
        return lessonRepository.save(lesson);
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public boolean isLessonApproved(Long lessonId, Map<Long, String> answers) {
        Lesson lesson = getLessonById(lessonId);
        double totalScore = 0;
        double correctScore = 0;

        for (Question question : lesson.getQuestions()) {
            String studentAnswer = answers.get(question.getId());
            totalScore += question.getScore();
            if (isAnswerCorrect(question, studentAnswer)) {
                correctScore += question.getScore();
            }
        }

        return correctScore >= lesson.getApprovalThreshold();
    }

    private boolean isAnswerCorrect(Question question, String studentAnswer) {
        switch (question.getType()) {
            case BOOLEAN:
            case MULTIPLE_CHOICE_ONE:
                return question.getCorrectAnswer().equals(studentAnswer);
            case MULTIPLE_CHOICE_MULTIPLE:
            case MULTIPLE_CHOICE_ALL_CORRECT:
                List<String> correctAnswers = question.getCorrectAnswers();
                List<String> studentAnswers = Arrays.asList(studentAnswer.split(","));
                return new HashSet<>(correctAnswers).containsAll(studentAnswers) && new HashSet<>(studentAnswers).containsAll(correctAnswers);
            default:
                return false;
        }
    }
}
