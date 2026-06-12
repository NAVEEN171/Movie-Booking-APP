package com.naveen.movieticketplatform.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="theaters")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String theaterName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;
    private Double distanceFromComplex;
    private Double distanceFromRailwayStation;

    @Column(nullable = false)
    private Integer noOfRows;

    @Column(nullable = false)
    private Integer noOfRowsInARow;

    @Column(nullable = false)
    private Integer noOfColumnsInARow;

    private Boolean defaultTimings;

    private Integer bufferTime;
    @Column(nullable = false)
    private String city;

    private Boolean isActive = true;


}
