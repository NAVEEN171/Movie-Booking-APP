package com.naveen.movieticketplatform.dto;

import com.naveen.movieticketplatform.enums.SeatType;
import com.naveen.movieticketplatform.enums.TheaterType;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class TheaterPricingDto {
    SeatType seatType;
    BigDecimal price;

}
