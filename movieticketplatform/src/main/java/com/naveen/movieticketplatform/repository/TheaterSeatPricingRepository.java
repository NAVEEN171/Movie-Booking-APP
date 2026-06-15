package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.entity.TheaterSeatPricing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterSeatPricingRepository extends JpaRepository<TheaterSeatPricing,Long> {
}
