package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.model.Student;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Log
public class StudentRepository {
    private final List<Student> students = new ArrayList<>();
    private Integer nextId = 1;

    public void addStudent(Student student) {
        student.setId(nextId++);
        students.add(student);
        log.info(String.format("Add student: %s", student.toString()));
    }
}
