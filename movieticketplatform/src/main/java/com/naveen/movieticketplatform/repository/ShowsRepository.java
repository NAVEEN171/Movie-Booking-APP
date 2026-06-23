package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowsRepository extends JpaRepository<Show,Long> {
}
