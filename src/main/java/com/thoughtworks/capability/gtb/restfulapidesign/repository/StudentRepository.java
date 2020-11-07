package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.model.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.model.Student;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Student> findAllByGender(String gender) {
        if (gender.equals("ALL")) {
            return students;
        }
        final Gender expectedGender;
        try {
            expectedGender = Gender.valueOf(gender);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("filter gender is invalid");
        }
        return students.stream()
                .filter(student -> expectedGender.equals(student.getGender()))
                .collect(Collectors.toList());
    }
}
