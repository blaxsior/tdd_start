package org.example.change;

import org.example.coin.CoinSet;

import java.util.List;

public class ChangeModule {
    public CoinSet getCoinSet(int cost) {
        var coinset = new CoinSet();
        var coinValues = List.of(10000, 5000, 1000, 500, 100, 50, 10);

        for(var coinValue: coinValues) {
            while(cost >= coinValue) {
                cost -= coinValue;
                coinset.addCoin(coinValue);
            }
        }

        return coinset;
    }
}
