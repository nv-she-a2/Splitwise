package com.example.myapp.repo;

import com.example.myapp.model.Group;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryGroupRepository implements GroupRepository {
    private final Map<String, Group> groups = new HashMap<>();

    @Override
    public Optional<Group> findById(String id) {
        return Optional.ofNullable(groups.get(id));
    }

    @Override
    public void save(Group group) {
        groups.put(group.getId(), group);
    }
}
