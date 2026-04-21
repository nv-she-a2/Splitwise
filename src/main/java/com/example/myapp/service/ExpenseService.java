package com.example.myapp.service;

import com.example.myapp.enums.SplitType;
import com.example.myapp.factory.SplitStrategyFactory;
import com.example.myapp.model.Expense;
import com.example.myapp.model.Group;
import com.example.myapp.model.Split;
import com.example.myapp.model.User;
import com.example.myapp.strategy.SplitStrategy;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class ExpenseService {

    private final BalanceSheetService balanceSheetService;

    public void addExpense(Group group, String description, double amount, User paidBy,
                           List<User> participants, SplitType splitType, Map<User, Double> metadata) {
        SplitStrategy strategy = SplitStrategyFactory.getStrategy(splitType);
        List<Split> splits = strategy.split(amount, participants, metadata);
        Expense expense = new Expense(description, amount, paidBy, splits, splitType);
        group.addExpense(expense);

        balanceSheetService.updateBalances(group, paidBy, splits);
    }
}
