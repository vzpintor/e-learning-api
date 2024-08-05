package com.elearnging.api.service;

import com.elearnging.api.entities.Lesson;

import java.util.List;
import java.util.Map;

public interface LessonService {

    List<Lesson> getAllLessons();

    Lesson getLessonById(Long id);

    Lesson createLesson(Lesson lesson);

    Lesson updateLesson(Long id, Lesson lessonDetails);

    void deleteLesson(Long id);

    boolean isLessonApproved(Long lessonId, Map<Long, String> answers);
}
