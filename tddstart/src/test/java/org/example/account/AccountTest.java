package org.example.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class AccountTest {
    @Test
    @DisplayName("현재 계좌 조회")
    public void getBalance() {

        var init_balance = 10000;
        var account = new Account(init_balance);

        var balance = account.getBalance();

        assertThat(balance).isEqualTo(init_balance);
    }

    @Test
    @DisplayName("입금 테스트")
    public void deposit() {
        var init_balance = 10000;
        var added_money=  1000;
        var account = new Account(init_balance);
        var expected = init_balance + added_money;

        account.deposit(added_money);
        var result = account.getBalance();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("출금 테스트")
    public void withdraw() {
        var init_balance = 10000;
        var reduced_money = 1000;
        var account = new Account(init_balance);
        var expected = init_balance - reduced_money;

        account.withdraw(reduced_money);
        var result = account.getBalance();

        assertThat(result).isEqualTo(expected);
    }
}