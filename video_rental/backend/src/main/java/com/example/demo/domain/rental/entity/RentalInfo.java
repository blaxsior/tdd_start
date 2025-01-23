package com.example.demo.domain.rental.entity;

import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.video.entity.Video;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "rental_info")
// TODO 쿼리 최적화
public class RentalInfo {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "cost", nullable = false)
    private Integer cost = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "user_id", updatable = false, insertable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    @Column(name = "video_id", updatable = false, insertable = false)
    private Long videoId;
}
