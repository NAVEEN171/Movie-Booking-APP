package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
