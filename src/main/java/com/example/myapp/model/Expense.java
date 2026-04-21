package com.example.myapp.model;

import com.example.myapp.enums.SplitType;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Expense {
    private String description;
    private double amount;
    private User paidBy;
    private List<Split> splits;
    private SplitType splitType;
}
