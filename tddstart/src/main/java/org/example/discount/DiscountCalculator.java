package org.example.discount;

import org.example.coupon.ICoupon;
import org.example.item.Item;

public class DiscountCalculator {

    public int getOrderPrice(Item item, ICoupon coupon) {
        if(coupon.isValid() && coupon.isApplicable(item)) {
            var discountRate = discountRate(coupon.getDiscountPercent());
            return (int)(item.getPrice() * discountRate);
        } else {
            return item.getPrice();
        }
    }

    private double discountRate(int percent) {
        return (100 - percent) / 100d;
    }
}
