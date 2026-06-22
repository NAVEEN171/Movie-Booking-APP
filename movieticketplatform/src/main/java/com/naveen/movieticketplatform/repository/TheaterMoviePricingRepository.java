package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.entity.TheaterMoviePricing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterMoviePricingRepository  extends JpaRepository<TheaterMoviePricing,Long> {
}
