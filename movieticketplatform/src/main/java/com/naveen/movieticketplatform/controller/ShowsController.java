package com.naveen.movieticketplatform.controller;

import com.naveen.movieticketplatform.service.ShowSeatsGenerateScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/shows")
@RequiredArgsConstructor
public class ShowsController {
    private  final ShowSeatsGenerateScheduler showSeatsGenerateScheduler;
    @PostMapping("shows-for-specified-day")
    public ResponseEntity<List<Long>> generateShowsForSpecifiedDay(@RequestParam("specifiedDay") LocalDate specifiedDay){
        return ResponseEntity.ok(showSeatsGenerateScheduler.generateShowsForTheNextDay(specifiedDay));
    }
}
