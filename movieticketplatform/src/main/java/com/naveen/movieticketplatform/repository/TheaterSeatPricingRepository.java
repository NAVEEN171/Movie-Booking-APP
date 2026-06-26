package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.entity.TheaterSeatPricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheaterSeatPricingRepository extends JpaRepository<TheaterSeatPricing,Long> {

    @Query(value = """
             select tsp.* from theater_seat_pricing tsp
              join main_theaters mt on tsp.main_theater_id=mt.id
              join theaters t on t.main_theater_id=mt.id where t.id= :theaterId and tsp.is_active = true
            """,nativeQuery = true)
    List<TheaterSeatPricing> getTheaterBasicSeatPrices(@Param("theaterId") Long theaterId);
}
