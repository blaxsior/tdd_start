package org.example.coin;

public enum CoinEnum {
    KRW500(500),
    KRW100(100),
    KRW50(50),
    KRW10(10);

    private final int value;

    CoinEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
