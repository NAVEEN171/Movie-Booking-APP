package com.naveen.movieticketplatform.service;


import com.naveen.movieticketplatform.dto.TheaterMovieRequest;
import com.naveen.movieticketplatform.dto.TimingsRequestDto;
import com.naveen.movieticketplatform.entity.Movie;
import com.naveen.movieticketplatform.entity.Theater;
import com.naveen.movieticketplatform.entity.TheaterMovie;
import com.naveen.movieticketplatform.entity.TimingMain;
import com.naveen.movieticketplatform.repository.MovieRepository;
import com.naveen.movieticketplatform.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheaterMovieService {
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final  TimingsService timingsService;

    public TheaterMovie linkTheaterToMovie(TheaterMovieRequest request){

        Optional<Movie> movie=movieRepository.findById(request.getMovieId());
        if(movie.isEmpty()){
            throw new NoSuchElementException("provided movie " + request.getMovieId()+" not found! ");
        }
        Optional<Theater> theater= theaterRepository.findById(request.getTheaterId());
        if(theater.isEmpty()){
            throw new NoSuchElementException("provided movie " + request.getTheaterId()+" not found! ");
        }

        TheaterMovie theaterMovie=new TheaterMovie();
        theaterMovie.setMovie(movie.get());
        theaterMovie.setTheater(theater.get());

        TimingMain savedTimings=timingsService.createTheaterTimings(request.getTimings());
        return null;





    }


}
