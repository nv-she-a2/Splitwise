package com.example.myapp.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class BalanceSheet {
    private double totalPaid;
    private double totalExpense;
    Map<User, Double> balances = new HashMap<>();

    public void addTotalPaid(double amount) {
        this.totalPaid += amount;
    }

    public void addTotalExpense(double amount) {
        this.totalExpense += amount;
    }

    public void addBalance(User other, double amount) {
        balances.put(other, balances.getOrDefault(other, 0.0) +  amount);
        if(Math.abs(balances.get(other)) < 1e-2)
            balances.remove(other);
    }

    public void clearBalance() {
        balances.clear();
    }

    public void printBalances(User me) {
        System.out.printf("Total paid: %.2f%n$", totalPaid);
        System.out.printf("Total expense: %.2f%n$", totalExpense);
        for(Map.Entry<User, Double> entry : balances.entrySet()) {
            User other = entry.getKey();
            if(entry.getValue() < 0.0)
                System.out.printf("You owe " + other.getUsername() + " %.2f%n$", entry.getValue());
            else
                System.out.printf(other.getUsername() + " owe you %.2f%n$", entry.getValue());
        }
    }
}
