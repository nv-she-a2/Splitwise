package com.example.myapp.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Group {
    private String id;
    private String name;
    private List<User> members = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();
    Map<User, BalanceSheet> balancesSheets = new HashMap<>();

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addMember(User user) {
        members.add(user);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public BalanceSheet getBalanceSheet(User user) {
        return balancesSheets.get(user);
    }
}
