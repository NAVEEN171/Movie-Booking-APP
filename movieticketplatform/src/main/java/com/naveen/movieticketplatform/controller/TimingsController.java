package com.naveen.movieticketplatform.controller;


import com.naveen.movieticketplatform.dto.TimingsRequestDto;
import com.naveen.movieticketplatform.entity.TimingMain;
import com.naveen.movieticketplatform.service.TimingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/timings")
@RequiredArgsConstructor
public class TimingsController {

    private final TimingsService timingsService;

    @PostMapping
    public ResponseEntity<TimingMain> createTheaterTimings(@RequestBody TimingsRequestDto timings){
             TimingMain timingsMain = timingsService.createTheaterTimings(timings);
             return ResponseEntity.status(HttpStatus.CREATED).body(timingsMain);

    }

}
