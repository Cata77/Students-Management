package com.gestiune.app.service;

import com.gestiune.app.model.Student;
import com.gestiune.app.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public List<Student> getStudentList() {
        return studentRepository.findAllStudents();
    }
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public Optional<Student> findStudentById(Long id) {
        return studentRepository.findStudentById(id);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteStudentsById(id);
    }

    public BigDecimal calculateAverageGrade() {
        return studentRepository.findAverageGrade();
    }

    public String getMostPopularProfile() {
        return studentRepository.findMostPopularProfile();
    }
}
