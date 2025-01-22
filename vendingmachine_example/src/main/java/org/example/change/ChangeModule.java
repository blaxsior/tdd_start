package org.example.change;

import org.example.coin.CoinEnum;
import org.example.coin.CoinSet;

import java.util.Arrays;

public class ChangeModule {
    public CoinSet getCoinSet(int cost) {
        var coinset = new CoinSet();
        var coinValues = Arrays
                .stream(CoinEnum.values())
                .map(CoinEnum::getValue)
                .sorted((a,b) -> b - a).toList();

        for(var coinValue: coinValues) {
            if(cost < coinValue) continue;

            int coinCount = cost / coinValue;
            cost -= (coinCount * coinValue);

            for(int i = 0; i < coinCount; i++) {
                coinset.addCoin(coinValue);
            }
        }

        return coinset;
    }
}
