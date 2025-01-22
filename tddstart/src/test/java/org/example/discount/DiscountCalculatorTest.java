package org.example.discount;

import org.example.coupon.FakeCoupon;
import org.example.coupon.ICoupon;
import org.example.coupon.SpyCoupon;
import org.example.coupon.StubCoupon;
import org.example.item.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DiscountCalculatorTest {
    @Test
    @DisplayName("getOrderPrice: 쿠폰을 적용할 수 있다면 할인 비율을 반영한다.")
    public void getOrderPrice_if_discountable() {
        var calculator = new DiscountCalculator();
        Item item = new Item("LightSavor", "부엌칼", 100000);
        ICoupon coupon = new StubCoupon();

        var expected = 93000;

        var result = calculator.getOrderPrice(item, coupon);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("getOrderPrice: 쿠폰을 적용할 수 없다면 원가를 반환한다.")
    public void getOrderPrice_original_if_not_discountable() {
        var calculator = new DiscountCalculator();
        Item item = new Item("LightSavor", "부엌칼", 100000);
        ICoupon coupon = new StubCoupon();

        var expected = 93000;

        var result = calculator.getOrderPrice(item, coupon);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("getOrderPrice: 쿠폰을 적용할 수 없다면 원가를 반환한다(fake)")
    public void getOrderPrice_original_if_not_discountable_fake() {
        var calculator = new DiscountCalculator();
        Item item = new Item("LightSavor", "부엌칼", 100000);
        ICoupon coupon = new FakeCoupon();

        var expected = 93000;

        var result = calculator.getOrderPrice(item, coupon);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("getOrderPrice: 쿠폰을 적용할 수 없다면 원가를 반환한다(spy)")
    public void getOrderPrice_original_if_not_discountable_spy() {
        var calculator = new DiscountCalculator();
        Item item = new Item("LightSavor", "부엌칼", 100000);
        SpyCoupon coupon = new SpyCoupon();

        var expected = 93000;

        var result = calculator.getOrderPrice(item, coupon);

        assertThat(result).isEqualTo(expected);
        assertThat(coupon.getIsApplicableCallCount()).isEqualTo(1);
    }
}