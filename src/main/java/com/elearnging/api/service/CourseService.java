package com.elearnging.api.service;

import com.elearnging.api.entities.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    Course createCourse(Course course);

    Course updateCourse(Long id, Course courseDetails);

    void deleteCourse(Long id);

    boolean isCourseApproved(Long courseId, Map<Long, Map<Long, String>> answers);
}
