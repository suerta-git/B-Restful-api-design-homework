package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.model.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Integer addStudent(Student student) {
        return studentRepository.add(student);
    }

    public Student getStudent(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id not exists"));
    }

    public List<Student> getStudents(String gender) {
        return studentRepository.findAllByGender(gender);
    }

    public void deleteStudent(int id) {
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("id not exists");
        }
    }
}
