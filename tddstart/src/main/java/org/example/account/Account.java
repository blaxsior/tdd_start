package org.example.account;

public class Account {
    private int balance;
    public Account(int initBalance) {
        this.balance = initBalance;
    }

    public int getBalance() {
        return this.balance;
    }

    public void deposit(int balance) {
        this.balance += balance;
    }

    public void withdraw(int balance) {
        this.balance -= balance;
    }
}
