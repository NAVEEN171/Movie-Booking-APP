package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.entity.TheaterMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TheaterMovieRepository extends JpaRepository<TheaterMovie,Long> {

    @Query(value = """
        SELECT * FROM theater_movies tm
        WHERE tm.movie_id = :movieId
        AND tm.theater_id = :theaterId
        AND tm.is_active = true
        """, nativeQuery = true)
    Optional<TheaterMovie> findActiveByMovieIdAndTheaterId(
            @Param("movieId") Long movieId,
            @Param("theaterId") Long theaterId
    );

}
