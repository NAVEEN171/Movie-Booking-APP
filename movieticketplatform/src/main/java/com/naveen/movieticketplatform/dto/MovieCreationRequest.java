package com.naveen.movieticketplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class MovieCreationRequest {
    @NotBlank
    private String movieName;

    @NotNull
    @Size(min=20)
    private String description;

    private String posterUrl;

    private String trailerUrl;

    @NotNull
    private LocalDate releaseDate;

    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;


    @NotNull
    private Integer durationInMin;

    @NotNull
    private Long theaterId;

    private List<Long> genres;

    private List<Long> languages;

    private List<Long> formats;

    @NotNull
    private Long censorRatingId;

    private Boolean defaultTimings;

    private TimingsRequestDto timings;

}
