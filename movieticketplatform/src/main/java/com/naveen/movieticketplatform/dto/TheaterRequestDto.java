package com.naveen.movieticketplatform.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TheaterRequestDto {

    @NotBlank
    private String theaterName;

    @NotBlank
    private String location;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    private Double distanceFromComplex;
    private Double distanceFromRailwayStation;

    private Set<Integer> reclinerRowIndexes = new HashSet<>();
    private Set<Integer> regularRowIndexes = new HashSet<>();

    @NotNull
    @Min(1)
    private Integer noOfRows;

    @NotNull
    @Min(1)
    private Integer noOfColumnsInARow;

    @NotNull
    @Min(1)
    private Integer noOfRowsInARow;



    private Boolean defaultTimings = true;
    private Integer bufferTime;

    @NotBlank
    private String city;
}
