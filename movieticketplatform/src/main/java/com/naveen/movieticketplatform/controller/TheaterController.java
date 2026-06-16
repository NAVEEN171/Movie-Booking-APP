package com.naveen.movieticketplatform.controller;

import com.naveen.movieticketplatform.dto.TheaterCreationRequestDto;
import com.naveen.movieticketplatform.dto.TheaterRequestDto;
import com.naveen.movieticketplatform.entity.MainTheater;
import com.naveen.movieticketplatform.entity.Theater;
import com.naveen.movieticketplatform.service.TheaterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/theaters")
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;

    @PostMapping
    public ResponseEntity<MainTheater> createTheater(@Valid @RequestBody TheaterCreationRequestDto request) {
        MainTheater mainTheater = theaterService.createTheater(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mainTheater);
    }
}