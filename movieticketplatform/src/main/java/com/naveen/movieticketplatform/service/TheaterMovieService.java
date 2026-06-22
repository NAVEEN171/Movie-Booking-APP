package com.naveen.movieticketplatform.service;


import com.naveen.movieticketplatform.dto.TheaterMovieRequest;
import com.naveen.movieticketplatform.dto.TheaterPricingDto;
import com.naveen.movieticketplatform.dto.TimingsRequestDto;
import com.naveen.movieticketplatform.entity.*;
import com.naveen.movieticketplatform.enums.TimingsType;
import com.naveen.movieticketplatform.repository.MovieRepository;
import com.naveen.movieticketplatform.repository.TheaterMoviePricingRepository;
import com.naveen.movieticketplatform.repository.TheaterMovieRepository;
import com.naveen.movieticketplatform.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheaterMovieService {
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final  TimingsService timingsService;
    private final TheaterMovieRepository theaterMovieRepository;
    private final TheaterMoviePricingRepository theaterMoviePricingRepository;


@Transactional
    public String linkTheaterToMovie(TheaterMovieRequest request){

        Optional<Movie> movie=movieRepository.findById(request.getMovieId());
        if(movie.isEmpty()){
            throw new NoSuchElementException("provided movie " + request.getMovieId()+" not found! ");
        }
        Optional<Theater> theater= theaterRepository.findById(request.getTheaterId());
        if(theater.isEmpty()){
            throw new NoSuchElementException("provided theater " + request.getTheaterId()+" not found! ");
        }

        TheaterMovie theaterMovie=new TheaterMovie();
        theaterMovie.setMovie(movie.get());
        theaterMovie.setTheater(theater.get());
        theaterMovie.setIsActive(true);
        theaterMovie.setStartDate(request.getStartDate());
        theaterMovie.setEndDate(request.getEndDate());
        TheaterMovie savedTheaterMovie=theaterMovieRepository.save(theaterMovie);

        TimingMain savedTimings=timingsService.createTheaterTimings(request.getTimings());
        theaterMovie.setTimingsMain(savedTimings);
        if(TimingsType.DEFAULT.equals(request.getTimingsType())){
            theaterMovie.setDefaultTimings(true);
        }
        else{
            theaterMovie.setDefaultTimings(false);
        }
        theaterMovieRepository.save(savedTheaterMovie);


        for(TheaterPricingDto theaterPrice:request.getTicketPrices()){
            TheaterMoviePricing theaterMoviePricing=new TheaterMoviePricing();
            theaterMoviePricing.setTheater(theater.get());
            theaterMoviePricing.setTheaterMovie(savedTheaterMovie);
            theaterMoviePricing.setStartTime(LocalDate.now());
            theaterMoviePricing.setOverridePrice(theaterPrice.getPrice());
            theaterMoviePricing.setSeatType(theaterPrice.getSeatType());
            theaterMoviePricing.setCreatedBy(1L);
            theaterMoviePricingRepository.save(theaterMoviePricing);
        }


        return "Created Successfully";
    }
}
