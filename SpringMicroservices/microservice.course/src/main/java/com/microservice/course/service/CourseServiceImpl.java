package com.microservice.course.service;

import com.microservice.course.client.StudentClient;
import com.microservice.course.entities.Course;
import com.microservice.course.entities.dto.StudentDTO;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.persistence.CourseRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CourseImpl")
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final CourseRepository courseRepository;
    private final StudentClient studentClient;
    @Override
    public List<Course> findAllCourse() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course findCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(()-> new NotFoundException(
                "THIS COURSE DOES NOT EXIST!"));
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public StudentByCourseResponse findStudentByIdCourse(Long idCourse) {
        Course course = courseRepository.findById(idCourse).orElse(null);

        List<StudentDTO> studentDTOList = studentClient.findAllStudentByCourse(idCourse);

        return course != null ? StudentByCourseResponse.builder()
                .courseName(course.getName())
                .teacher(course.getTeacher())
                .studentList(studentDTOList)
                .build() : null;
    }
}
