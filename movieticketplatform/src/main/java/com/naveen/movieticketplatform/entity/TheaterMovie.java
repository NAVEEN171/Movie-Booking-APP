package com.naveen.movieticketplatform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "theater_movies")
public class TheaterMovie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timing_main_id")
    private TimingMain timingsMain;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Boolean defaultTimings = true;

    @Column(nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "theaterMovie", fetch = FetchType.LAZY)
    private List<TheaterMoviePricing> pricingOverrides;
}