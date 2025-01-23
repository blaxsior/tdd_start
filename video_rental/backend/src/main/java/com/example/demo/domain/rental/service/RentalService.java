package com.example.demo.domain.rental.service;

import com.example.demo.domain.rental.entity.RentalInfo;
import com.example.demo.domain.rental.repository.RentalInfoRepository;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.domain.video.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class RentalService {
    private final RentalInfoRepository rentalRepository;
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;

    public RentalService(
            RentalInfoRepository rentalInfoRepository,
            UserRepository userRepository,
            VideoRepository videoRepository
    ) {
        this.rentalRepository = rentalInfoRepository;
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
    }

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

    @Transactional(readOnly = true)
    public Long getRentalCount(Long userId) {
        return this.rentalRepository.countByUserId(userId);
    }
}
