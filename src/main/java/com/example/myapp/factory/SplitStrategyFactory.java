package com.example.myapp.factory;

import com.example.myapp.enums.SplitType;
import com.example.myapp.strategy.EqualSplitStrategy;
import com.example.myapp.strategy.PercentageSplitStrategy;
import com.example.myapp.strategy.SplitStrategy;

public class SplitStrategyFactory {
    public static SplitStrategy getStrategy(SplitType splitType) {
        return switch (splitType){
            case EQUAL ->  new EqualSplitStrategy();
            case PERCENTAGE ->  new PercentageSplitStrategy();
        };
    }
}
