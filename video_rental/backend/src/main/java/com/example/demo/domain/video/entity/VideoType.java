package com.example.demo.domain.video.entity;

import com.example.demo.domain.rental.cost.entity.CostPolicy;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "video_type")
public class VideoType {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "videoType")
    private List<Video> videos;

    @OneToMany(mappedBy = "videoType")
    private List<CostPolicy> costPolicies;
}
