package com.naveen.movieticketplatform.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TheaterMovieRequest {

    @NotNull
    private Long movieId;
    @NotNull
    private Long theaterId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private TimingsRequestDto timings;

    private List<TheaterPricingDto> ticketPrices;
}
