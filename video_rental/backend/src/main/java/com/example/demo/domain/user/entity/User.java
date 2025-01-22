package com.example.demo.domain.user.entity;

import com.example.demo.domain.rental.entity.RentalInfo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int point;

    @OneToMany(mappedBy = "user")
    private List<RentalInfo> rentalInfos;
}
