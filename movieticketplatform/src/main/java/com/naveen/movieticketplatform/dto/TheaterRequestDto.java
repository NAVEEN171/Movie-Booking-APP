package com.naveen.movieticketplatform.dto;

import com.naveen.movieticketplatform.enums.TheaterType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class TheaterRequestDto {

    @NotBlank
    private String theaterName;



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



}
