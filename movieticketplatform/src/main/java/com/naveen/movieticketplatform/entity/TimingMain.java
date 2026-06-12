package com.naveen.movieticketplatform.entity;


import com.naveen.movieticketplatform.enums.TimingsType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="timings_main")
public class TimingMain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    @Column(nullable = false)
    private Integer bufferTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TimingsType timingsType;

    private LocalTime startTime;

    @OneToMany(mappedBy = "timingsMain", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Timing> timings = new ArrayList<>();

    public void addTiming(Timing timing) {
        timings.add(timing);
        timing.setTimingsMain(this);
    }
}
