package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.model.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.model.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class GroupService {
    private final StudentService studentService;
    private final GroupRepository groupRepository;

    public GroupService(StudentService studentService, GroupRepository groupRepository) {
        this.studentService = studentService;
        this.groupRepository = groupRepository;
    }

    public void regroup(int total) {
        if (total < 1) {
            throw new IllegalArgumentException("Total group number should >= 1");
        }
        final List<Student> students = new LinkedList<>(studentService.getStudents());
        int[] groupSizes = getGroupSizes(total, students);
        updateGroups(students, groupSizes);
    }

    private void updateGroups(List<Student> students, int[] groupSizes) {
        final List<Group> groups = groupRepository.findAll();
        for (int groupIndex = 0; groupIndex < groupSizes.length; groupIndex++) {
            final List<Student> groupedStudents = groupStudentsOf(students, groupSizes[groupIndex]);
            if (groupIndex < groups.size()) {
                groups.get(groupIndex).setStudents(groupedStudents);
            } else {
                final Group newGroup = Group.builder()
                        .name(String.format("%d 组", groupIndex + 1))
                        .students(groupedStudents).build();
                groupRepository.add(newGroup);
            }
        }

        if (groupSizes.length < groups.size()) {
            groupRepository.deleteLastOf(groups.size() - groupSizes.length);
        }
    }

    private List<Student> groupStudentsOf(List<Student> students, int groupSize) {
        final List<Student> groupedStudents = new ArrayList<>();
        for (int i = 0; i < groupSize; i++) {
            Random random = new Random();
            final int studentIndex = random.nextInt(students.size());
            groupedStudents.add(students.get(studentIndex));
            students.remove(studentIndex);
        }
        return groupedStudents;
    }

    private int[] getGroupSizes(int total, List<Student> students) {
        final int[] groupSizes = new int[total];
        for (int i = 0; i < total; i++) {
            groupSizes[i] = i < students.size() % total
                    ? students.size() / total + 1
                    : students.size() / total;
        }
        return groupSizes;
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public void updateGroup(int id, Group groupPatch) {
        try {
            groupRepository.update(id, groupPatch);
        } catch (Exception e) {
            throw new IllegalArgumentException("id not exists");
        }
    }
}
