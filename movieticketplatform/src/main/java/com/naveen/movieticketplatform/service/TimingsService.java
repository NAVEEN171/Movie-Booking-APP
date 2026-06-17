package com.naveen.movieticketplatform.service;

import com.naveen.movieticketplatform.dto.TimingDto;
import com.naveen.movieticketplatform.dto.TimingsRequestDto;
import com.naveen.movieticketplatform.entity.Movie;
import com.naveen.movieticketplatform.entity.Theater;
import com.naveen.movieticketplatform.entity.Timing;
import com.naveen.movieticketplatform.entity.TimingMain;
import com.naveen.movieticketplatform.enums.TimingsType;
import com.naveen.movieticketplatform.repository.MovieRepository;
import com.naveen.movieticketplatform.repository.TheaterRepository;
import com.naveen.movieticketplatform.repository.TimingMainRepository;
import com.naveen.movieticketplatform.repository.TimingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TimingsService {

    private final TimingMainRepository timingMainRepository;
    private final TimingRepository timingRepository;
    private final TheaterRepository theaterRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public TimingMain createTheaterTimings(TimingsRequestDto timings) {

//        Theater theater = theaterRepository.findById(timings.getTheaterId())
//                .orElseThrow(() -> new NoSuchElementException("Theater not found: " + timings.getTheaterId()));
//
//        Movie movie = movieRepository.findById(timings.getMovieId())
//                .orElseThrow(() -> new NoSuchElementException("Movie not found: " + timings.getMovieId()));
//
//        TimingMain timingMain = new TimingMain();
//        timingMain.setTheater(theater);
//        timingMain.setTimingsType(timings.getTimingsType());
//        timingMain.setBufferTime(timings.getBufferTime());
//        timingMain.setStartTime(timings.getStartTime());
//
//        if (TimingsType.MANUAL.equals(timings.getTimingsType())) {
//
//            for (TimingDto timing : timings.getTimingsDto()) {
//                Timing newTiming = new Timing();
//                newTiming.setBufferTime(timing.getBufferTime());
//                newTiming.setStartTime(timing.getStartTime());
//                newTiming.setEndTime(timing.getEndTime());
//                newTiming.setApplicableTill(movie.getEndDate());
//                timingMain.addTiming(newTiming);
//            }
//        } else {
//            for (int i = 0; i < timings.getNoOfShows(); i++) {
//                LocalTime baseStartTime = timings.getStartTime();
//                Integer movieDuration = movie.getDurationInMin();
//                Integer bufferMinutes = timings.getBufferTime();
//
//                Integer offsetMinutes = i * (movieDuration + timings.getBufferTime());
//                LocalTime startTime = baseStartTime.plusMinutes(offsetMinutes);
//                LocalTime endTime = startTime.plusMinutes(movieDuration);
//
//                Timing newTiming = new Timing();
//                newTiming.setStartTime(startTime);
//                newTiming.setEndTime(endTime);
//                newTiming.setBufferTime(bufferMinutes);
//                newTiming.setApplicableTill(movie.getEndDate());
//
//                timingMain.addTiming(newTiming);
//            }
//        }
//
//        return timingMainRepository.save(timingMain);
        return null;
    }
}
