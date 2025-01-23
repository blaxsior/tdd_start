package com.example.demo.domain.rental.cost.entity;

import com.example.demo.domain.video.entity.VideoType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "cost_policy")
public class CostPolicy {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_type_id")
    private VideoType videoType;

    @Column(name ="video_type_id", updatable = false, insertable = false)
    private Long videoTypeId;

    private int minRentDay;
    private int discountValue;
}
