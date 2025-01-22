package com.example.demo.domain.rental.service;

import com.example.demo.domain.rental.repository.RentalInfoRepository;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.domain.video.repository.VideoRepository;
import org.hibernate.annotations.processing.SQL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@Sql(value = "classpath:sql/refresh.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = "classpath:sql/RentalService.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class RentalServiceTest {
    @Autowired
    RentalInfoRepository rentalRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    VideoRepository videoRepository;

    RentalService rentalService;

    @BeforeEach
    void beforeEach() {
        rentalService = new RentalService(
                rentalRepository,
                userRepository,
                videoRepository
        );
    }

    @Test
    @DisplayName("getRentalCount: 고객이 대여 중인 총 비디오 개수를 반환한다. (정상 상태)")
    void getRentalCount_return_all_rental_item_count() {
        //given
        long userId = 1; // 유저 1이
        int dayLong = 3; // 3일간 빌린다.
        long expected = 2; // 빌린 개수
        // when 유저가 2개를 빌리면

        rentalService.addRentalInfo(userId, 1, dayLong);
        rentalService.addRentalInfo(userId, 2, dayLong);

        // then 빌린 개수 2를 반환
        long result = rentalService.getRentalCount(userId);

        assertThat(result).isEqualTo(expected);
    }
}