package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.entity.Timing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimingRepository extends JpaRepository<Timing,Long> {
}
