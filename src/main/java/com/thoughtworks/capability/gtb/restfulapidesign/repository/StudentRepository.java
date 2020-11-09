package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.model.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.model.Student;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Log
public class StudentRepository {
    private final Map<Integer, Student> students = new HashMap<>();
    private Integer nextId = 1;

    public void add(Student student) {
        student.setId(nextId++);
        students.put(student.getId(), student);
        log.info(String.format("Add student: %s", student.toString()));
    }

    public Optional<Student> findById(int id) {
        return Optional.ofNullable(students.get(id));
    }

    public List<Student> findAllByGender(String gender) {
        if (gender.equals("ALL")) {
            return new ArrayList<>(students.values());
        }
        final Gender expectedGender;
        try {
            expectedGender = Gender.valueOf(gender);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("filter gender is invalid");
        }
        return students.values().stream()
                .filter(student -> expectedGender.equals(student.getGender()))
                .collect(Collectors.toList());
    }

    public void deleteById(int id) {
        findById(id).orElseThrow(() -> new IllegalArgumentException("Repository internal error: Id not exists"));
        students.remove(id);
    }
}
