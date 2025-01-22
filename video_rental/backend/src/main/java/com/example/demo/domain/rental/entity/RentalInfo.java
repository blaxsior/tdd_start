package com.example.demo.domain.rental.entity;

import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.video.entity.Video;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@Table(name = "rental_info")
public class RentalInfo {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="video_id", nullable = false)
    private Video video;
}
