package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.model.Group;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.capability.gtb.restfulapidesign.util.RepositoryUtils.updateIfNotNull;

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

    public void update(int id, Group groupPatch) {
        if (id < 1 || id > groups.size()) {
            throw new IllegalArgumentException("Repository internal error: Id not exists");
        }
        final Group group = groups.get(id - 1);
        group.setName(updateIfNotNull(group.getName(), groupPatch.getName()));
    }
}
