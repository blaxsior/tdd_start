package org.example.coupon;

import org.example.item.Item;

public class StubCoupon implements ICoupon {
    @Override
    public String getName() {
        return "VIP 고객 설날 감사 쿠폰";
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
        return item.getCategory().equals("부엌칼");
    }

    @Override
    public void doExpire() {}
}
