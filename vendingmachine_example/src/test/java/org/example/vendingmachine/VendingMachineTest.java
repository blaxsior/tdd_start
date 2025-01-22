package org.example.vendingmachine;

import org.example.coin.CoinSet;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class VendingMachineTest {
    @Test
    public void addBalance_should_add_all_input_balance() {
        var machine = new VendingMachine();

        machine.addBalance(100);
        assertThat(machine.getBalance()).isEqualTo(100);

        machine.addBalance(500);
        assertThat(machine.getBalance()).isEqualTo(600);
    }

    @Test
    public void returnBalance_should_return_all_remain_balance_by_coinset() {
        var machine = new VendingMachine();
        // 700원 삽입
        machine.addBalance(100);
        machine.addBalance(100);
        machine.addBalance(500);

        var expected = new CoinSet();
        expected.addCoin(50);

        machine.purchaseItem(new Item("음료수", 650));
        var result = machine.returnBalance();

        assertThat(result).isEqualTo(expected);
        assertThat(machine.getBalance()).isEqualTo(0);
    }
}