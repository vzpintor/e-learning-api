package com.elearnging.api.repository;

import com.elearnging.api.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByPreviousCourseIsNull();
}

