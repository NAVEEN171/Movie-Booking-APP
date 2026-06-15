package com.naveen.movieticketplatform.service;


import com.naveen.movieticketplatform.dto.TheaterPricingDto;
import com.naveen.movieticketplatform.dto.TheaterRequestDto;
import com.naveen.movieticketplatform.dto.TimingDto;
import com.naveen.movieticketplatform.dto.TimingsRequestDto;
import com.naveen.movieticketplatform.entity.Theater;
import com.naveen.movieticketplatform.entity.Timing;
import com.naveen.movieticketplatform.entity.TimingMain;
import com.naveen.movieticketplatform.enums.TimingsType;
import com.naveen.movieticketplatform.repository.TheaterRepository;
import com.naveen.movieticketplatform.repository.TimingMainRepository;
import com.naveen.movieticketplatform.repository.TimingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class TimingsService {

    private final TimingMainRepository timingMainRepository;
    private final TimingRepository timingRepository;
    private final TheaterRepository theaterRepository;


    @Transactional
    public TimingMain createTheaterTimings(TimingsRequestDto timings){

        Theater theater=theaterRepository.findById(timings.getTheaterId())
                .orElseThrow(() -> new RuntimeException("Theater not found: " + timings.getTheaterId()));
        TimingMain timingMain=new TimingMain();
        timingMain.setTheater(theater);
        timingMain.setTimingsType(timings.getTimingsType());
        timingMain.setBufferTime(timings.getBufferTime());
        timingMain.setStartTime(timings.getStartTime());

        if(TimingsType.MANUAL.equals(timings.getTimingsType())) {

            for (TimingDto timing : timings.getTimingsDto()) {
                Timing newTiming = new Timing();
                newTiming.setBufferTime(timing.getBufferTime());
                newTiming.setStartTime(timing.getStartTime());
                newTiming.setEndTime(timing.getEndTime());
                newTiming.setApplicableTill(timing.getApplicableTill());
                timingMain.addTiming(newTiming);
            }
        }
        else{
             for(int i=0;i<timings.getNoOfShows();i++){
                 LocalTime baseStartTime = timings.getStartTime();
                 Integer movieDuration = 120;
                 Integer bufferMinutes = timings.getBufferTime();

                 Integer offsetMinutes = i * (movieDuration+timings.getBufferTime());
                 LocalTime startTime = baseStartTime.plusMinutes(offsetMinutes);
                 LocalTime endTime =startTime.plusMinutes(movieDuration);

                 Timing newTiming = new Timing();
                 newTiming.setStartTime(startTime);
                 newTiming.setEndTime(endTime);
                 newTiming.setBufferTime(bufferMinutes);
                 newTiming.setApplicableTill(LocalDate.now().plusDays(5));

                 timingMain.addTiming(newTiming);


             }
        }


        return timingMainRepository.save(timingMain);
    }
}
