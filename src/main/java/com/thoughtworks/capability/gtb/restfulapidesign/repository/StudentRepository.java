package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.model.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.model.Student;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static com.thoughtworks.capability.gtb.restfulapidesign.util.RepositoryUtils.updateIfNotNull;

@Repository
@Log
public class StudentRepository {
    private final Map<Integer, Student> students = new HashMap<>();
    private Integer nextId = 1;

    public Integer add(Student student) {
        student.setId(nextId++);
        students.put(student.getId(), student);
        log.info(String.format("Add student: %s", student.toString()));
        return student.getId();
    }

    public Optional<Student> findById(int id) {
        return Optional.ofNullable(students.get(id));
    }

    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    public List<Student> findAllByGender(Gender gender) {
        return students.values().stream()
                .filter(student -> gender.equals(student.getGender()))
                .collect(Collectors.toList());
    }

    public void deleteById(int id) {
        findById(id).orElseThrow(() -> new IllegalArgumentException("Repository internal error: Id not exists"));
        students.remove(id);
    }

    public void update(int id, Student studentPatch) {
        final Student student = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Repository internal error: Id not exists"));
        student.setName(updateIfNotNull(student.getName(), studentPatch.getName()));
        student.setGender(updateIfNotNull(student.getGender(), studentPatch.getGender()));
        student.setNote(updateIfNotNull(student.getNote(), studentPatch.getNote()));
    }
}
