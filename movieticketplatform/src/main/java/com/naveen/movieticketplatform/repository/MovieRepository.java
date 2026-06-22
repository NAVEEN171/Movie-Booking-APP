package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.dto.MovieListResponse;
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

//    @Query(value = """
//""")

}
