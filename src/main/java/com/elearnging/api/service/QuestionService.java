package com.elearnging.api.service;

import com.elearnging.api.entities.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {

    List<Question> getAllQuestions();

    Question getQuestionById(Long id);

    Question createQuestion(Question question);

    Question updateQuestion(Long id, Question questionDetails);

    void deleteQuestion(Long id);
}
