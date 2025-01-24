package com.example.demo.domain.rental.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RentalDetailDto {
    /**
     * 비디오 종류
     */
    private String type;

    /**
     * 비디오 이름
     */
    private String name;

    /**
     * 비디오 대여 요금
     */
    private int rentalCost;

    /**
     * 대여 시작
     */
    private LocalDate startDate;

    /**
     * 대여 만기일
     */
    private LocalDate dueDate;
}
