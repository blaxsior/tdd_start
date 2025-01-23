package com.example.demo.domain.rental.service;

import com.example.demo.domain.rental.entity.RentalInfo;
import com.example.demo.domain.rental.repository.RentalInfoRepository;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.domain.video.repository.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@DataJpaTest
@Sql(value = "classpath:sql/refresh.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = "classpath:sql/RentalService.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class RentalServiceTest {
    @MockitoSpyBean
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

    @Test
    @DisplayName("addRentalInfo: 존재하지 않는 유저가 요청 시 예외가 발생한다.")
    void addRentalInfo_throw_if_user_not_exist() {
        long notExistUserId = 2; // 존재하지 않는 유저
        long videoId = 1;
        int dayLong = 3;

        assertThatThrownBy(() -> {
            rentalService.addRentalInfo(notExistUserId, 1, dayLong);
        }).hasMessageContaining("cannot find user");
    }

    @Test
    @DisplayName("addRentalInfo: 존재하지 않는 비디오를 요청 시 예외가 발생한다.")
    void addRentalInfo_throw_if_video_not_exist() {
        long userId = 1; 
        long notExistVideoId = 100; // 존재하지 않는 비디오
        int dayLong = 3;

        assertThatThrownBy(() -> {
            rentalService.addRentalInfo(userId, notExistVideoId, dayLong);
        }).hasMessageContaining("cannot find video");
    }

    @Test
    @DisplayName("addRentalInfo: 고객과 비디오가 정상이라면 아이템을 삽입한다.")
    void addRentalInfo_add_item() {
        long userId = 1;
        long videoId = 1;
        int dayLong = 3;
        var captor = ArgumentCaptor.forClass(RentalInfo.class);

        rentalService.addRentalInfo(userId, videoId, dayLong);

        verify(rentalRepository,
                times(1))
                .save(captor.capture());

        var rentalInfo = captor.getValue();

        assertThat(rentalInfo.getUser().getId()).isEqualTo(userId);
        assertThat(rentalInfo.getVideo().getId()).isEqualTo(videoId);
    }

    @Test
    @DisplayName("addRentalInfo: 이미 대여된 비디오를 요청 시 예외가 발생한다.")
    void addRentalInfo_throw_if_video_already_rented() {
        long userId = 1;
        long notExistVideoId = 1;
        int dayLong = 3;

        rentalService.addRentalInfo(userId, notExistVideoId, dayLong);

        assertThatThrownBy(() -> {
            rentalService.addRentalInfo(userId, notExistVideoId, dayLong);
        }).hasMessageContaining("video already rented");
    }



}