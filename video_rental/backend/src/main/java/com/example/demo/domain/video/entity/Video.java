package com.example.demo.domain.video.entity;

import com.example.demo.domain.rental.entity.RentalInfo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer rentalCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_type_id")
    private VideoType videoType;

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "video")
    private List<RentalInfo> rentalInfos;

    public RentalInfo getRentalInfo() {
        if(this.rentalInfos == null || this.rentalInfos.isEmpty()) return null;
        return this.rentalInfos.getFirst();
    }

    public void setRentalInfo(RentalInfo rentalInfo) {
        this.rentalInfos = new ArrayList<>();
        this.rentalInfos.add(rentalInfo);
    }
}
