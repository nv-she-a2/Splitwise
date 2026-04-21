package com.example.myapp.service;

import com.example.myapp.model.BalanceSheet;
import com.example.myapp.model.Group;
import com.example.myapp.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DebtSimplificationService {
    public void simplifyDebts(Group group) {
        List<User> users = group.getMembers();
        Map<User, BalanceSheet> sheets = group.getBalancesSheets();

        // calculate final or net balance of each user
        Map<User, Double> netBalances = new HashMap<>();
        for (User user : users) {
            double net = 0.0;
            Map<User, Double> balances = sheets.get(user).getBalances();
            for(double amount : balances.values()) {
                net += amount;
            }
            netBalances.put(user, net);
            sheets.get(user).clearBalance();
        }

        //Separate creditors and debtors
        PriorityQueue<User> creditors = new PriorityQueue<>((a,b) ->
                Double.compare(netBalances.get(b), netBalances.get(a)));
        PriorityQueue<User> debtors = new PriorityQueue<>((a,b) ->
                Double.compare(netBalances.get(a), netBalances.get(b)));
        for (User user : users) {
            double net = netBalances.get(user);
            if(net > 0.0) {
                creditors.add(user);
            } else if(net < 0.0) {
                debtors.add(user);
            }
        }

        while(!creditors.isEmpty() && !debtors.isEmpty()) {
            User creditor = creditors.poll();
            User debtor = debtors.poll();

            double creditAmt = netBalances.get(creditor);
            double debtorAmt = netBalances.get(debtor);

            double settledAmt = Math.min(creditAmt, -debtorAmt);

            sheets.get(creditor).addBalance(creditor, -settledAmt);
            sheets.get(debtor).addBalance(debtor, settledAmt);

            netBalances.put(creditor, creditAmt - settledAmt);
            netBalances.put(debtor, debtorAmt + settledAmt);

            if(netBalances.get(creditor) > 0.0) {
                creditors.add(creditor);
            }
            if(netBalances.get(debtor) < 0.0) {
                debtors.add(debtor);
            }
        }
    }
}
