package com.naveen.movieticketplatform.entity;


import com.naveen.movieticketplatform.enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seat_seq")
    @SequenceGenerator(name = "seat_seq", sequenceName = "seat_seq_id", allocationSize = 50)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    @Column(nullable = false)
    private String seatNo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType seatType;

    private Boolean isActive = true;

}
