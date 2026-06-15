package com.naveen.movieticketplatform.repository;

import com.naveen.movieticketplatform.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieLanguageRepository extends JpaRepository<Language,Long> {

}
