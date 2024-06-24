package com.microservice.course.service;

import com.microservice.course.entities.Course;
import com.microservice.course.http.response.StudentByCourseResponse;

import java.util.List;

public interface ICourseService {

    List<Course> findAllCourse();
    Course findCourseById(Long id);
    void save(Course course);
    StudentByCourseResponse findStudentByIdCourse(Long idCourse);
}
