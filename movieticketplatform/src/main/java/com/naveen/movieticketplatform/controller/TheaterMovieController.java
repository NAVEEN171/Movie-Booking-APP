package com.naveen.movieticketplatform.controller;


import com.naveen.movieticketplatform.dto.TheaterMovieRequest;
import com.naveen.movieticketplatform.entity.TheaterMovie;
import com.naveen.movieticketplatform.service.TheaterMovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/theater-movie")
@RequiredArgsConstructor
public class TheaterMovieController {
    private final TheaterMovieService theaterMovieService;

    @PostMapping
    public ResponseEntity<String> linkTheaterToMovie(@Valid  @RequestBody TheaterMovieRequest request){
      return ResponseEntity.ok(theaterMovieService.linkTheaterToMovie(request));
    }
}
