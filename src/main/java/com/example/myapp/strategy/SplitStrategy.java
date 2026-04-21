package com.example.myapp.strategy;

import com.example.myapp.model.Split;
import com.example.myapp.model.User;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    public List<Split> split(double amount, List<User> participants, Map<User, Double> metadata);
}
