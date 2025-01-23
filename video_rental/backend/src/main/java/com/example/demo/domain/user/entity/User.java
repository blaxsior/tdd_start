package com.example.demo.domain.user.entity;

import com.example.demo.domain.rental.entity.RentalInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "point", nullable = false)
    private int point;

    @OneToMany(mappedBy = "user")
    private List<RentalInfo> rentalInfos;
}
