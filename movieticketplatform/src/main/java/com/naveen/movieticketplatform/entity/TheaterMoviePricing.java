package com.naveen.movieticketplatform.entity;

import com.naveen.movieticketplatform.enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "theater_movie_pricing")
public class TheaterMoviePricing extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_movie_id", nullable = false)
    private TheaterMovie theaterMovie;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType seatType;

    @Column(nullable = false)
    private BigDecimal overridePrice;

    @Column(nullable = false)
    private LocalDate startTime;


    private LocalDate endTime;
}
