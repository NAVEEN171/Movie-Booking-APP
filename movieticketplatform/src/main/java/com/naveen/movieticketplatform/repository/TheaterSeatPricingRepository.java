package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.entity.TheaterSeatPricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheaterSeatPricingRepository extends JpaRepository<TheaterSeatPricing,Long> {

    @Query(value = """
            select * from theater_seat_pricing tsp where tsp.theater_id = theaterId and tsp.is_active = 1 
            """,nativeQuery = true)
    List<TheaterSeatPricing> getTheaterBasicSeatPrices(@Param("theaterId") Long theaterId);
}
