package com.naveen.movieticketplatform.dto;


import com.naveen.movieticketplatform.enums.TheaterType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class TheaterCreationRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    private Double distanceFromComplex;
    private Double distanceFromRailwayStation;

    @NotBlank
    private String city;

    @NotNull
    private TheaterType theaterType;

    private List<TheaterPricingDto> theaterBasePricing;

    private List<TheaterRequestDto> subTheaters;

}
