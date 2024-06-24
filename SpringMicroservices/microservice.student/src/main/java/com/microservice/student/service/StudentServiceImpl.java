package com.microservice.student.service;

import com.microservice.student.entities.Student;
import com.microservice.student.persistence.StudentRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StudentServiceImpl")
@RequiredArgsConstructor

public class StudentServiceImpl implements IStudentService{

    private final StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(()-> new NotFoundException(
                "THIS STUDENT DOES NOT EXIST!"));
    }

    @Override
    public void save(Student student) {

        studentRepository.save(student);

    }

    @Override
    public List<Student> findByIdCourse(Long idCourse) {
        return studentRepository.findAllStudentByCourseId(idCourse);
    }
}
