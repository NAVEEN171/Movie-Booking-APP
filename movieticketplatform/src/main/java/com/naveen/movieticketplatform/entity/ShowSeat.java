package com.naveen.movieticketplatform.entity;
import com.naveen.movieticketplatform.enums.ShowSeatStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "show_seats")
public class ShowSeat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "show_seat_seq")
    @SequenceGenerator(name = "show_seat_seq", sequenceName = "show_seat_sequence", allocationSize = 50)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShowSeatStatus status = ShowSeatStatus.AVAILABLE;

    @Column(nullable = false)
    private BigDecimal finalPrice;
}
