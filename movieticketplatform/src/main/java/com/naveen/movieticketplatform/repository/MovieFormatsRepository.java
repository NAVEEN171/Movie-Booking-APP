package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.entity.Format;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieFormatsRepository extends JpaRepository<Format,Long> {
}
