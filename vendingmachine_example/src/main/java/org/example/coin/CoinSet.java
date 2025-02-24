package org.example.coin;

import java.util.ArrayList;
import java.util.List;

public class CoinSet {
    private final List<Integer> coins = new ArrayList<>();

    public void addCoin(int coin) {
        this.coins.add(coin);
    }
    public void addCoin(CoinEnum coin) {
        this.coins.add(coin.getValue());
    }

    @Override
    public boolean equals(Object coinSet) {
        if(!(coinSet instanceof CoinSet)) return false;

        return this.toString().equals(coinSet.toString());
    }

    @Override
    public String toString() {
        var coin_str_list = coins.stream().sorted().map(String::valueOf).toList();
        return String.join(" ", coin_str_list);
    }

    public int getTotalBalance() {
        return this.coins.stream()
                .reduce(Integer::sum)
                .orElse(0);
    }
}
