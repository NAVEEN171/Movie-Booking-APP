package com.naveen.movieticketplatform.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TimingDto {
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer bufferTime;
    private LocalDate applicableTill;
}
