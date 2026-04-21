package com.example.myapp.service;

import com.example.myapp.enums.SplitType;
import com.example.myapp.model.Group;
import com.example.myapp.model.User;
import com.example.myapp.repo.GroupRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class GroupService {
    private GroupRepository groupRepository;
    private ExpenseService expenseService;
    private DebtSimplificationService simplifier;

    public String createGroup(String name, List<User> members) {
        String groupId = UUID.randomUUID().toString();
        Group group = new Group(groupId, name);
        members.forEach(group::addMember);
        groupRepository.save(group);
        return groupId;
    }

    public void addMember(String groupId, User user) {
        get(groupId).addMember(user);
    }

    public void addExpense(String groupId, String description, double amount, User paidBy,
                           List<User> participants, SplitType splitType, Map<User, Double> metadata) {
        expenseService.addExpense(get(groupId), description, amount, paidBy, participants, splitType, metadata);
    }

    public void simplifyDebts(String groupId) {
        simplifier.simplifyDebts(get(groupId));
    }

    public Group get(String groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found: " + groupId));
    }
}
