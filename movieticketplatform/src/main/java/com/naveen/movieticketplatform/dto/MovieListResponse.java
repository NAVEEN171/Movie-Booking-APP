package com.naveen.movieticketplatform.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;



@Data
@AllArgsConstructor
public class MovieListResponse {
    private Long id;
    private Long mainTheaterId;
    private String movieName;
    private String description;
    private String posterUrl;
    private Integer durationInMin;
    private LocalDate releaseDate;

}
