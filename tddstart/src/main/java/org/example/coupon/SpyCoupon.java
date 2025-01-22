package org.example.coupon;

import org.example.item.Item;

import java.util.Set;

public class SpyCoupon implements ICoupon{
    private final Set<String> categories;

    private int isApplicableCallCount = 0;


    public SpyCoupon() {
        this.categories = Set.of("부엌칼", "제과");
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int getDiscountPercent() {
        return 7;
    }

    @Override
    public boolean isApplicable(Item item) {
        isApplicableCallCount++;
        return categories.contains(item.getCategory());
    }

    @Override
    public void doExpire() {

    }

    public int getIsApplicableCallCount() {
        return isApplicableCallCount;
    }
}
