package com.naveen.movieticketplatform.controller;


import com.naveen.movieticketplatform.dto.MovieCreationRequest;
import com.naveen.movieticketplatform.dto.MovieListResponse;
import com.naveen.movieticketplatform.dto.TheaterMovieListResponse;
import com.naveen.movieticketplatform.entity.Movie;
import com.naveen.movieticketplatform.service.MovieService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long movieId){
         return ResponseEntity.ok(movieService.getMovie(movieId));
    }

    @GetMapping("/movies-per-location")
    public ResponseEntity<List<MovieListResponse>> getMoviesInALocation(@RequestParam  String location){
        return ResponseEntity.ok(movieService.getMoviesInALocation(location));
    }

    @GetMapping("/theaters-in-location")
    public ResponseEntity<List<TheaterMovieListResponse>> getTheatersInALocationByMovie(@RequestParam String location, @RequestParam Long movieId){
        return ResponseEntity.ok(movieService.getMoviesByLocationAndMovieId(location,movieId));
    }
}
