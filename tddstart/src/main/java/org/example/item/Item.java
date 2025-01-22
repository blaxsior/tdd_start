package org.example.item;

public class Item {
    private final int price;
    private final String category;

    public Item(String lightSavor, String category, int price) {
        this.category = category;
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public String getCategory() {
        return category;
    }
}
