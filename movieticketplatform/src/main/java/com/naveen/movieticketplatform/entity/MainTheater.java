package com.naveen.movieticketplatform.entity;

import com.naveen.movieticketplatform.enums.TheaterType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="main_theaters")
public class MainTheater extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TheaterType theaterType;

    @Column(nullable = false)
    private String location;


    @Column(nullable = false)
    private Double latitude;


    @Column(nullable = false)
    private Double longitude;

    private Double distanceFromComplex;


    private Double distanceFromRailwayStation;

    private Boolean isActive;
}
