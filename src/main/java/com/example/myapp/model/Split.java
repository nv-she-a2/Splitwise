package com.example.myapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class Split {
    private User user;
    private double amount;
}
