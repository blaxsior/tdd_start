package org.example.user;

import org.example.coupon.DummyCoupon;
import org.example.coupon.ICoupon;
import org.example.coupon.StubCoupon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class UserTest {
    @Test
    @DisplayName("getTotalCouponCount: 쿠폰 없으면 0")
    public void getTotalCouponCount_zero_if_no_coupon_set() {
        var user = new User("area88");
        var expected = 0;

        var count = user.getTotalCouponCount();

        assertThat(count).isEqualTo(expected);
    }

    @Test
    @DisplayName("getTotalCouponCount: 쿠폰을 획득했으면 쿠폰의 개수")
    public void getTotalCouponCount_N_if_user_get_coupon() {
        var user = new User("area88");
        var expected = 1;
        ICoupon coupon = new DummyCoupon();

        user.addCoupon(coupon);

        var count = user.getTotalCouponCount();

        assertThat(count).isEqualTo(expected);
    }

    @Test
    @DisplayName("getLastOccupiedCoupon: 쿠폰이 없으면 null 반환")
    public void getLastOccupiedCoupon_return_null_if_no_coupon() {
        var user = new User("");

        var coupon = user.getLastOccupiedCoupon();
        assertThat(coupon).isNull();
    }

    @Test
    @DisplayName("getLastOccupiedCoupon: 쿠폰이 있으면 마지막 쿠폰 반환")
    public void getLastOccupiedCoupon_return_last_coupon_if_exist() {
        var user = new User("");
        var coupon1 = new DummyCoupon();
        var coupon2 = new StubCoupon();

        user.addCoupon(coupon1);
        user.addCoupon(coupon2);

        var coupon = user.getLastOccupiedCoupon();
        assertThat(coupon).isNotNull();
        assertThat(coupon.getName()).contains("설날");
        assertThat(coupon.getDiscountPercent()).isEqualTo(7);
    }
}