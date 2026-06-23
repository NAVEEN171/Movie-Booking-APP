package com.naveen.movieticketplatform.repository;


import com.naveen.movieticketplatform.entity.Seat;
import com.naveen.movieticketplatform.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query(value = """
      select * from seat s where s.theater_id = :theaterId 
      """,nativeQuery = true)
    List<Seat>  getSeatsOfTheater(Long theaterId);
}
