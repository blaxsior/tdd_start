package org.example.coin;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class CoinSetTest {
    @Test
    void getTotalBalance_return_all_coin_value_sum() {
        var coinSet = new CoinSet();
        coinSet.addCoin(CoinEnum.KRW100);
        coinSet.addCoin(CoinEnum.KRW500);
        coinSet.addCoin(CoinEnum.KRW500);
        coinSet.addCoin(CoinEnum.KRW50);
        var expected = 1150;

        int totalBalance = coinSet.getTotalBalance();

        Assertions.assertThat(totalBalance).isEqualTo(expected);
    }
}