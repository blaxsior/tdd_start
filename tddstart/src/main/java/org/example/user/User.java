package org.example.user;

import org.example.coupon.ICoupon;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String username;
    private final List<ICoupon> couponList;

    public User(String name) {
        this.username = name;
        this.couponList = new ArrayList<>();
    }

    public int getTotalCouponCount() {
        return couponList.size();
    }

    public void addCoupon(ICoupon coupon) {
        couponList.add(coupon);
    }

    public ICoupon getLastOccupiedCoupon() {
        ICoupon coupon;
        try {
            coupon = couponList.getLast();
        } catch (Exception e) {
            coupon = null;
        }

        return coupon;
    }
}
