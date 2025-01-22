package com.example.demo.domain.rental.repository;

import com.example.demo.domain.rental.entity.RentalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentalInfoRepository extends JpaRepository<RentalInfo, Long> {

    @Query("select count(r) from RentalInfo r where r.user.id = :userId")
    Long countByUserId(Long userId);
}
