package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieGenreRepository extends JpaRepository<Genre,Long> {
}
