package com.naveen.movieticketplatform.controller;


import com.naveen.movieticketplatform.dto.MovieCreationRequest;
import com.naveen.movieticketplatform.entity.Movie;
import com.naveen.movieticketplatform.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;


    @PostMapping
    public ResponseEntity<List<Movie>> createMovieInTheater(@Valid @RequestBody List<MovieCreationRequest> movieRequest){

    return ResponseEntity.ok(movieService.createMovie(movieRequest));

    }
}
