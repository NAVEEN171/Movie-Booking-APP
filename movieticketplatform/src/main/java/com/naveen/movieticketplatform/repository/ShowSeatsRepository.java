package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.entity.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowSeatsRepository extends JpaRepository<ShowSeat,Long> {
}
