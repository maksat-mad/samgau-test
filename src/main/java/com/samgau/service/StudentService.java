package com.samgau.service;

import com.samgau.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAllStudents();

    Student findStudentById(Long id);

    Student findStudentByName(String name);
}
