package org.example.vendingmachine;

import org.example.coin.CoinSet;

import java.util.List;

public class VendingMachine {
    private int balance;

    public void addBalance(int balance) {
        this.balance += balance;
    }

    public int getBalance() {
        return balance;
    }

    public void purchaseItem(Item item) {
        int cost = item.getCost();
        //TODO 예외 처리
        if (cost > this.balance) return;

        this.balance -= cost;
    }

    public CoinSet returnBalance() {
        var coinset = new CoinSet();
        var coinValues = List.of(10000, 5000, 1000, 500, 100, 50, 10);
        var tempBalance = this.balance;

        for(var coinValue: coinValues) {
            while(tempBalance >= coinValue) {
                tempBalance -= coinValue;
                coinset.addCoin(coinValue);
            }
        }
        this.balance = tempBalance; // 남는 금액이 없어야 하는데...
        return coinset;
    }
}
