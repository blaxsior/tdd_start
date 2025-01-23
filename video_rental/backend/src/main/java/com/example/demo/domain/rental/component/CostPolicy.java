package com.example.demo.domain.rental.component;

import com.example.demo.domain.video.entity.VideoType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CostPolicy {
    private VideoType videoType;
    private int minRentDay;
    private int discountValue;
}
