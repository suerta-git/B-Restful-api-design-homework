package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.model.Student;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Log
public class StudentRepository {
    private final List<Student> students = new ArrayList<>();
    private Integer nextId = 1;

    public void add(Student student) {
        student.setId(nextId++);
        students.add(student);
        log.info(String.format("Add student: %s", student.toString()));
    }

    public Optional<Student> findById(int id) {
        return Optional.ofNullable(
                id > students.size() || id < 1 ? null : students.get(id - 1)
        );
    }
}
