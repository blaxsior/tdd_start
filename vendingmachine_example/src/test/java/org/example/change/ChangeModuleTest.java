package org.example.change;

import org.example.coin.CoinSet;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChangeModuleTest {
    @Test
    public void getCoinSet_should_return_coinset() {
        var module = new ChangeModule();
        var expected = new CoinSet();
        expected.addCoin(50);
        expected.addCoin(10);
        expected.addCoin(100);

        var result = module.getCoinSet(160);

        assertThat(result).isEqualTo(expected);
    }
}