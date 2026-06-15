package com.naveen.movieticketplatform.entity;


import com.naveen.movieticketplatform.enums.TimingsType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name="timings")
public class Timing extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name="timings_main_id",nullable = false)
    private TimingMain timingsMain;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private Integer bufferTime;



    private LocalDate applicableTill;

    @Column(nullable = false)
    private Boolean isActive = true;
}
