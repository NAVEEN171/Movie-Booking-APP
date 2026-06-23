package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.dto.MovieListResponse;
import com.naveen.movieticketplatform.dto.TheaterMovieListResponse;
import com.naveen.movieticketplatform.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query(value = """
      select  DISTINCT ON (mv.id, mt.id) mv.id ,mt.id as main_theater_id,
      mv.movie_name,mv.description,mv.poster_url,
      mv.duration_in_min,mv.release_date from main_theaters mt
      join theaters t on mt.id=t.main_theater_id
      join theater_movies tm on t.id=tm.theater_id
      join movies mv on mv.id=tm.movie_id
      where mt.city ILIKE :location
""",nativeQuery = true)
    List<MovieListResponse> getMoviesInALocation(@Param("location") String location);

    @Query(value = """

            SELECT
    mv.id AS movie_id,
    mt.id AS main_theater_id,
    t.id AS theater_id,
    mt.name as main_theater_name,
    t.theater_name as theater_name,
    MAX(CASE WHEN tsp.seat_type = 'REGULAR' THEN tsp.base_price END) AS starting_price,
    MAX(CASE WHEN tmp.seat_type = 'REGULAR' THEN tmp.override_price END) AS starting_desired_price
    FROM main_theaters mt
    JOIN theaters t ON mt.id = t.main_theater_id
    JOIN theater_movies tm ON t.id = tm.theater_id
    JOIN movies mv ON mv.id = tm.movie_id
    LEFT JOIN theater_seat_pricing tsp ON tsp.main_theater_id = mt.id
    LEFT JOIN theater_movie_pricing tmp ON tm.id = tmp.theater_movie_id AND t.id = tmp.theater_id

    where mt.city ILIKE :location and mv.id= :movieId
    GROUP BY mv.id, mt.id, t.id, mt.name, t.theater_name
""",nativeQuery = true)
    List<TheaterMovieListResponse> getTheaterByMovieAndLocation(@Param("location") String location, @Param("movieId") Long movieId);




}
