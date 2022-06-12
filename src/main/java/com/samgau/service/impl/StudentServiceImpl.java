package com.samgau.service.impl;

import com.samgau.entity.Student;
import com.samgau.exception.NotFoundException;
import com.samgau.repository.StudentRepository;
import com.samgau.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Student not found with ID %d", id)));
    }

    @Override
    public Student findStudentByName(String name) {
        return studentRepository.findAll()
                .stream()
                .filter(el -> el.getStudentName().equals(name))
                .findAny()
                .orElseThrow(() -> new NotFoundException(String.format("Student not found with name %s", name)));
    }
}
