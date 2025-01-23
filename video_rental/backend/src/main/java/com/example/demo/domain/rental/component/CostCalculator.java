package com.example.demo.domain.rental.component;

import com.example.demo.domain.video.entity.Video;


public class CostCalculator {
    public int calculateCost(Video video, int dayLong, CostPolicy policy) {
        var pricePerDay = video.getRentalCost();
        var defaultCost = pricePerDay * dayLong;

        // (1) 정책이 존재하는지 검사
        if(policy == null) return defaultCost;

        var minRentDay = policy.getMinRentDay();
        var percent = policy.getDiscountValue();

        // (1) 기간이 최소대여일을 초과하는지 검사
        if(minRentDay >= dayLong) return defaultCost;
        
        // (2) 해당 타입이 맞는지 검사
        var policyTargetId = policy.getVideoType().getId();
        var videoTypeId = video.getVideoType().getId();
        if(!videoTypeId.equals(policyTargetId)) return defaultCost;
        
        // (3) 할인 정책 적용
        return pricePerDay * minRentDay
                + getDiscountCost(pricePerDay, percent) * (dayLong - minRentDay);
    }

    private int getDiscountCost(int cost, int percent) {
        return (int)(cost * (100 - percent) / 100d);
    }
}
