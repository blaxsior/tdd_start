package com.example.demo.domain.rental.repository;

import com.example.demo.domain.rental.entity.RentalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RentalInfoRepository extends JpaRepository<RentalInfo, Long> {

    @Query("select count(r) from RentalInfo r where r.user.id = :userId")
    Long countByUserId(Long userId);

    // TODO 쿼리 최적화
//    @Query("select count(r) from RentalInfo r where r.video.id = :videoId limit 1")
    boolean existsByVideoId(Long videoId);

    @Query("select r from RentalInfo r join fetch r.video where r.userId = :userId")
    List<RentalInfo> findByUserIdWithFetch(Long userId);
}
