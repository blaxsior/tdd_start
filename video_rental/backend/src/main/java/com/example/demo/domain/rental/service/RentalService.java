package com.example.demo.domain.rental.service;

import com.example.demo.domain.rental.cost.component.CostCalculator;
import com.example.demo.domain.rental.cost.service.CostPolicyService;
import com.example.demo.domain.rental.entity.RentalInfo;
import com.example.demo.domain.rental.repository.RentalInfoRepository;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.domain.video.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class RentalService {
    private final RentalInfoRepository rentalRepository;
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;

    private final CostPolicyService policyService;
    private final CostCalculator costCalculator;

    public RentalService(
            RentalInfoRepository rentalInfoRepository,
            UserRepository userRepository,
            VideoRepository videoRepository,
            CostPolicyService policyService,
            CostCalculator costCalculator
    ) {
        this.rentalRepository = rentalInfoRepository;
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
        this.policyService = policyService;
        this.costCalculator = costCalculator;
    }

    /**
     * 사용자가 비디오를 빌린다
     * @param userId 사용자 Id
     * @param videoId 비디오 Id
     * @param dayLong 비디오 빌리는 기간
     */
    @Transactional
    public void addRentalInfo(long userId, long videoId, int dayLong) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("cannot find user"));
        var video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("cannot find video"));

        var videoAlreadyExist = rentalRepository.existsByVideoId(videoId);
        if(videoAlreadyExist) throw new RuntimeException("video already rented");

        var startDate = LocalDate.now();
        var dueDate = startDate.plusDays(dayLong);

        var rentalInfo = RentalInfo.builder()
                .user(user)
                .video(video)
                .startDate(startDate)
                .dueDate(dueDate)
                .build();

        rentalRepository.save(rentalInfo);
    }

    /**
     * 사용자가 렌탈 중인 비디오 개수를 반환한다.
     * @param userId 유저 id
     * @return 빌린 개수
     */
    @Transactional(readOnly = true)
    public Long getRentalCount(Long userId) {
        return this.rentalRepository.countByUserId(userId);
    }

    @Transactional(readOnly = true)
    public int getTotalCost(Long userId) {
        var rentalInfos =  this.rentalRepository.findByUserIdWithFetch(userId);

        int totalCost = 0;

        for(var rental: rentalInfos) {
            var video = rental.getVideo();
            var policy = policyService.findMatchedPolicy(video.getVideoTypeId());
            int dayLong = (int)ChronoUnit.DAYS.between(rental.getStartDate(), rental.getDueDate());
            totalCost += costCalculator.calculateCost(video, dayLong,policy);
        }

        return totalCost;
    }
}
