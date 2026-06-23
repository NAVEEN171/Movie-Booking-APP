package com.naveen.movieticketplatform.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TheaterMovieListResponse {
    private Long movieId;
    private Long mainTheaterId;
    private Long theaterId;
    private String mainTheaterName;
    private String theaterName;
    private BigDecimal startingPrice;
    private BigDecimal startingDesiredPrice;
}
