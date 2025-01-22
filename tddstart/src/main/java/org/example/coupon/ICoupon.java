package org.example.coupon;

import org.example.item.Item;

/**
 * Coupon에 대한 인터페이스
 */
public interface ICoupon {
    String getName();
    boolean isValid();
    int getDiscountPercent();

    /**
     * 쿠폰을 대상 아이템에 적용할 수 있는지 검사한다.
     * @param item 아이템
     */
    boolean isApplicable(Item item);

    /**
     * 쿠폰을 만료한다.
     */
    void doExpire();
}
