package com.example.demo.domain.rental.component;


import com.example.demo.domain.video.entity.Video;
import com.example.demo.domain.video.entity.VideoType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CostCalculatorTest {
    CostCalculator calculator;

    @BeforeEach
    void beforeEach() {
        this.calculator = new CostCalculator();
    }

    @Test
    @DisplayName("calculateCost: 비디오, 대여 기간, 가격 정책이 주어지면 가격을 계산한다.")
    void calculateCost_return_discount_cost() {
        // 비디오 / 대여 기간 / 가격 정책이 주어질 때 가격을 계산
        var type = VideoType.builder().id(1L).name("documentary").build();
        Video video = Video.builder().id(0L).rentalCost(6000)
                .title("test").videoType(type).build();
        int dayLong = 6;

        // 3일 이후부터는 50% 할인
        CostPolicy costPolicy = CostPolicy.builder()
                .discountValue(50)
                .minRentDay(3)
                .videoType(type)
                .build();

        int expected = 27000; // 6000 * 3 + 3000 * 3;

        int result = calculator.calculateCost(video, dayLong, costPolicy);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("calculateCost: 비디오 타입이 다른 정책은 적용되지 않는다.")
    void calculateCost_policy_not_applied_if_video_type_different() {
        // 비디오 / 대여 기간 / 가격 정책이 주어질 때 가격을 계산
        var type = VideoType.builder().id(1L).name("documentary").build();
        var type2 = VideoType.builder().id(2L).name("sports").build();
        Video video = Video.builder().id(0L).rentalCost(6000)
                .title("test").videoType(type2).build();
        int dayLong = 6;

        // 3일 이후부터는 50% 할인
        CostPolicy costPolicy = CostPolicy.builder()
                .discountValue(50)
                .minRentDay(3)
                .videoType(type)
                .build();

        int expected = 36000; // no discount

        int result = calculator.calculateCost(video, dayLong, costPolicy);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("calculateCost: 정책이 없으면 기본 가격을 반환한다.")
    void calculateCost_return_default_if_no_policy() {
        // 비디오 / 대여 기간 / 가격 정책이 주어질 때 가격을 계산
        var type = VideoType.builder().id(1L).name("documentary").build();
        Video video = Video.builder().id(0L).rentalCost(6000)
                .title("test").videoType(type).build();
        int dayLong = 6;
        int expected = 36000; // no discount

        int result = calculator.calculateCost(video, dayLong, null);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("calculateCost: 기간이 최소 대여일을 초과하지 않으면 원가를 반환한다.")
    void calculateCost_return_default_if_rent_day_not_enough() {
        // 비디오 / 대여 기간 / 가격 정책이 주어질 때 가격을 계산
        var type = VideoType.builder().id(1L).name("documentary").build();
        Video video = Video.builder().id(0L).rentalCost(6000)
                .title("test").videoType(type).build();
        // 3일 이후부터는 50% 할인
        CostPolicy costPolicy = CostPolicy.builder()
                .discountValue(50)
                .minRentDay(3)
                .videoType(type)
                .build();
        int dayLong = 1;

        int expected = 6000; // no discount

        int result = calculator.calculateCost(video, dayLong, costPolicy);
        assertThat(result).isEqualTo(expected);
    }
}