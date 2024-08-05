package com.elearnging.api.service.impl;

import com.elearnging.api.entities.Course;
import com.elearnging.api.entities.Lesson;
import com.elearnging.api.exceptions.ResourceNotFoundException;
import com.elearnging.api.repository.CourseRepository;
import com.elearnging.api.service.CourseService;
import com.elearnging.api.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final LessonService lessonService;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course courseDetails) {
        Course course = getCourseById(id);
        course.setTitle(courseDetails.getTitle());
        course.setDescription(courseDetails.getDescription());
        course.setPreviousCourse(courseDetails.getPreviousCourse());
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public boolean isCourseApproved(Long courseId, Map<Long, Map<Long, String>> answers) {
        Course course = getCourseById(courseId);
        for (Lesson lesson : course.getLessons()) {
            if (!lessonService.isLessonApproved(lesson.getId(), answers.get(lesson.getId()))) {
                return false;
            }
        }
        return true;
    }
}
