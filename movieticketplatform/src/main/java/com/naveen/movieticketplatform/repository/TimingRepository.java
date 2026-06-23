package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.entity.Timing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public interface TimingRepository extends JpaRepository<Timing,Long> {

    @Query(value = """
      select * from timings t where t.timings_main_id = :timingId and t.applicable_till> = :specifiedDay and t.is_active=true
    """,nativeQuery = true)
    List<Timing> getTimingsForTheMovie(Long timingId, LocalDate specifiedDay);
}
