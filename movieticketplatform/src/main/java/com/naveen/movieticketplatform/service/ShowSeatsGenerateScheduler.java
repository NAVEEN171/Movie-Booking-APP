package com.naveen.movieticketplatform.service;

import com.naveen.movieticketplatform.entity.*;
import com.naveen.movieticketplatform.enums.ShowSeatStatus;
import com.naveen.movieticketplatform.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShowSeatsGenerateScheduler {
    private final TheaterMovieRepository theaterMovieRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final TimingRepository timingRepository;
    private final ShowsRepository showsRepository;
    private final ShowSeatsRepository showSeatsRepository;
    private final SeatRepository seatRepository;

    public void generateShowsForTheNextDay(LocalDate movieDate){
        LocalDate needToGenerateDay=movieDate==null?LocalDate.now():movieDate;
        List<TheaterMovie> theaterMovies= theaterMovieRepository.fetchTheaterMoviesOnSpecifiedDay(needToGenerateDay);
        theaterMovies.stream().forEach(theaterMovie->{
            Movie movie=theaterMovie.getMovie();
            Theater theater=theaterMovie.getTheater();
            if(movie==null || movie.getIsActive().equals(Boolean.TRUE) || theater==null || Boolean.TRUE.equals(theater.getIsActive())){
                throw new NoSuchElementException("specified movie or theater is not active for the day");
            }
            List<Timing> timings=timingRepository.getTimingsForTheMovie(theaterMovie.getTimingsMain().getId(),needToGenerateDay);
            List<Seat> seats =seatRepository.getSeatsOfTheater(theater.getId());
            if(seats.isEmpty()){
                throw new IllegalArgumentException("For given Theater, There are no seats");
            }
            for(Timing timing:timings){
                Show newShow = new Show();
                newShow.setTheater(theater);
                newShow.setTiming(timing);
                newShow.setTheaterMovie(theaterMovie);
                newShow.setShowDate(needToGenerateDay);
                newShow.setIsActive(true);
                Show savedShow = showsRepository.save(newShow);

                for(Seat seat:seats){
                    ShowSeat newShowSeat=new ShowSeat();
                    newShowSeat.setSeat(seat);
                    newShowSeat.setShow(savedShow);
                    newShowSeat.setStatus(ShowSeatStatus.AVAILABLE);
//                    newShowSeat.setFinalPrice();

                }





            }
        });



    }
}
