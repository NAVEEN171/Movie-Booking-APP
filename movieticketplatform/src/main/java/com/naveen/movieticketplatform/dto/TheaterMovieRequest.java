package com.naveen.movieticketplatform.dto;

import com.naveen.movieticketplatform.enums.TimingsType;
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

    private TimingsType timingsType;

    private TimingsRequestDto timings;

    private List<TheaterPricingDto> ticketPrices;
}
