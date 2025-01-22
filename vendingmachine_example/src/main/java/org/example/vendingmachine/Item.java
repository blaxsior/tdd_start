package org.example.vendingmachine;

public class Item {
    private String name;
    private int cost;
    public Item(String name, int cost) {
        if(cost < 0) throw new RuntimeException("item cost must not be under 0");

        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}
