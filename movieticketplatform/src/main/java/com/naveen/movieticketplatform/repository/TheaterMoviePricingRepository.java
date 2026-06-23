package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.entity.TheaterMoviePricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheaterMoviePricingRepository  extends JpaRepository<TheaterMoviePricing,Long> {


    @Query(value = """
            select * from theater_movie_pricing tmp where tmp.theater_movie_id = :theaterMovieId and tmp.is_active = 1
            """,nativeQuery = true)
    List<TheaterMoviePricing> customMoviePricing(@Param("theaterMovieId") Long theaterMovieId);
}
