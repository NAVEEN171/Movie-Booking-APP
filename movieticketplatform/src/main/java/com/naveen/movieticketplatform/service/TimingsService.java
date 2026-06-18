package com.naveen.movieticketplatform.service;

import com.naveen.movieticketplatform.dto.TimingDto;
import com.naveen.movieticketplatform.dto.TimingsRequestDto;
import com.naveen.movieticketplatform.entity.*;
import com.naveen.movieticketplatform.enums.TimingsType;
import com.naveen.movieticketplatform.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimingsService {

    private final TimingMainRepository timingMainRepository;
    private final TimingRepository timingRepository;
    private final TheaterRepository theaterRepository;
    private final MovieRepository movieRepository;
    private final TheaterMovieRepository theaterMovieRepository;

    @Transactional
    public TimingMain createTheaterTimings(TimingsRequestDto timings) {

        Theater theater = theaterRepository.findById(timings.getTheaterId())
                .orElseThrow(() -> new NoSuchElementException("Theater not found: " + timings.getTheaterId()));

        Optional<TheaterMovie> theaterMovie=theaterMovieRepository.findActiveByMovieIdAndTheaterId(timings.getMovieId(), timings.getTheaterId());

        if(theaterMovie.isEmpty()){
            throw new NoSuchElementException("Theater and movie link doesn't exist");
        }

        Movie movie = movieRepository.findById(timings.getMovieId())
                .orElseThrow(() -> new NoSuchElementException("Movie not found: " + timings.getMovieId()));

        TimingMain timingMain = new TimingMain();
        timingMain.setTheater(theater);
        timingMain.setTimingsType(timings.getTimingsType());
        timingMain.setBufferTime(timings.getBufferTime());
        timingMain.setStartTime(timings.getStartTime());

        if (TimingsType.MANUAL.equals(timings.getTimingsType())) {

            for (TimingDto timing : timings.getTimingsDto()) {
                Timing newTiming = new Timing();
                newTiming.setBufferTime(timing.getBufferTime());
                newTiming.setStartTime(timing.getStartTime());
                newTiming.setEndTime(timing.getEndTime());
                newTiming.setApplicableTill(theaterMovie.get().getEndDate());
                timingMain.addTiming(newTiming);
            }
        } else {
            for (int i = 0; i < timings.getNoOfShows(); i++) {
                LocalTime baseStartTime = timings.getStartTime();
                Integer movieDuration = movie.getDurationInMin();
                Integer bufferMinutes = timings.getBufferTime();

                Integer offsetMinutes = i * (movieDuration + timings.getBufferTime());
                LocalTime startTime = baseStartTime.plusMinutes(offsetMinutes);
                LocalTime endTime = startTime.plusMinutes(movieDuration);

                Timing newTiming = new Timing();
                newTiming.setStartTime(startTime);
                newTiming.setEndTime(endTime);
                newTiming.setBufferTime(bufferMinutes);
                newTiming.setApplicableTill(theaterMovie.get().getEndDate());

                timingMain.addTiming(newTiming);
            }
        }

        return timingMainRepository.save(timingMain);
    }
}
