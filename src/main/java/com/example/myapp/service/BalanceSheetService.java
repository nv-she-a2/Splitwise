package com.example.myapp.service;

import com.example.myapp.model.Group;
import com.example.myapp.model.Split;
import com.example.myapp.model.User;

import java.util.List;

public class BalanceSheetService {

    public void updateBalances(Group group, User paidBy, List<Split> splits) {
        double amount = splits.stream().mapToDouble(Split::getAmount).sum();
        group.getBalanceSheet(paidBy).addTotalPaid(amount);
        for(Split split : splits) {
            User user = split.getUser();
            double amt = split.getAmount();
            group.getBalanceSheet(user).addTotalExpense(amt);
            if(!user.equals(paidBy)) {
                group.getBalanceSheet(user).addBalance(paidBy, -amt);
                group.getBalanceSheet(paidBy).addBalance(user, amt);
            }
        }
    }
}
