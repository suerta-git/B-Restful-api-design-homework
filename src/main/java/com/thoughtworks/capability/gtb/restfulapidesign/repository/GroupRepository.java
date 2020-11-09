package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.model.Group;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupRepository {
    private final List<Group> groups = new ArrayList<>();

    public List<Group> findAll() {
        return groups;
    }

    public void add(Group group) {
        group.setId(groups.size() + 1);
        groups.add(group);
    }

    public void deleteLastOf(int number) {
        final int size = groups.size();
        for (int i = 1; i <= number; i++) {
            groups.remove(size - i);
        }
    }
}
