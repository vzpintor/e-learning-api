package com.elearnging.api.service.impl;

import com.elearnging.api.entities.Question;
import com.elearnging.api.exceptions.ResourceNotFoundException;
import com.elearnging.api.repository.QuestionRepository;
import com.elearnging.api.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Long id, Question questionDetails) {
        Question question = getQuestionById(id);
        question.setText(questionDetails.getText());
        question.setScore(questionDetails.getScore());
        question.setType(questionDetails.getType());
        question.setOptions(questionDetails.getOptions());
        question.setCorrectAnswer(questionDetails.getCorrectAnswer());
        question.setCorrectAnswers(questionDetails.getCorrectAnswers());
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
