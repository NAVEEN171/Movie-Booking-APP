package com.naveen.movieticketplatform.entity;

import com.naveen.movieticketplatform.enums.TheaterType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="theaters")
public class Theater extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String theaterName;


    @Column(nullable = false)
    private Integer noOfRows;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_theater_id", nullable = false)
    private MainTheater mainTheater;

    @Column(nullable = false)
    private Integer noOfRowsInARow;

    @Column(nullable = false)
    private Integer noOfColumnsInARow;

    private Boolean defaultTimings;

    private Integer bufferTime;


    private Boolean isActive = true;


}
