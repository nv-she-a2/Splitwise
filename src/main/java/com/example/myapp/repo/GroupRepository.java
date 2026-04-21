package com.example.myapp.repo;

import com.example.myapp.model.Group;

import java.util.Optional;

public interface GroupRepository {
    Optional<Group> findById(String id);
    void save(Group group);
}
