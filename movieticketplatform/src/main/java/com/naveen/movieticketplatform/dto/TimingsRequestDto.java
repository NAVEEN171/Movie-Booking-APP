package com.naveen.movieticketplatform.dto;

import com.naveen.movieticketplatform.enums.TimingsType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class TimingsRequestDto {
    private Long theaterId;
    private Long movieId;
    private Integer bufferTime;
    private TimingsType timingsType;
    private Integer noOfShows;
    private LocalTime startTime;

    private List<TimingDto> timingsDto;
}
