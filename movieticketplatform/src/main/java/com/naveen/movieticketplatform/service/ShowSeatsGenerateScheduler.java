package com.naveen.movieticketplatform.service;

import com.naveen.movieticketplatform.entity.*;
import com.naveen.movieticketplatform.enums.SeatType;
import com.naveen.movieticketplatform.enums.ShowSeatStatus;
import com.naveen.movieticketplatform.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShowSeatsGenerateScheduler {
    private final TheaterMovieRepository theaterMovieRepository;
    private final TimingRepository timingRepository;
    private final ShowsRepository showsRepository;
    private final ShowSeatsRepository showSeatsRepository;
    private final SeatRepository seatRepository;
    private final TheaterMoviePricingRepository theaterMoviePricingRepository;
    private final TheaterSeatPricingRepository theaterSeatPricingRepository;

    @Transactional
    public List<Long> generateShowsForTheNextDay(LocalDate movieDate) {
        List<Long> successTheaters = new ArrayList<>();

        LocalDate needToGenerateDay = movieDate == null ? LocalDate.now() : movieDate;
        if (needToGenerateDay.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot generate a show for previous days");

        }
        log.info("started generating shows for the day {}",needToGenerateDay);

        List<TheaterMovie> theaterMovies = theaterMovieRepository.fetchTheaterMoviesOnSpecifiedDay(needToGenerateDay);
        theaterMovies.stream().forEach(theaterMovie -> {
            try {
                log.info("started generating show for the movie theater {}",theaterMovie.getId());
                Movie movie = theaterMovie.getMovie();
                Theater theater = theaterMovie.getTheater();
                if (movie == null || Boolean.FALSE.equals(movie.getIsActive()) || theater == null || Boolean.FALSE.equals(theater.getIsActive())) {
                    throw new NoSuchElementException("specified movie or theater is not active for the day");
                }
                List<Timing> timings = timingRepository.getTimingsForTheMovie(theaterMovie.getTimingsMain().getId(), needToGenerateDay);
                if (timings.isEmpty()) {
                    throw new NoSuchElementException("For the given Movie, No timings Exist");
                }
                List<Seat> seats = seatRepository.getSeatsOfTheater(theater.getId());
                if (seats.isEmpty()) {
                    throw new IllegalArgumentException("For given Theater, There are no seats");
                }

                List<TheaterMoviePricing> customTicketPrices = theaterMoviePricingRepository.customMoviePricing(theaterMovie.getId());
                Map<SeatType, BigDecimal> customPrices = customTicketPrices.stream()
                        .collect(Collectors.toMap(TheaterMoviePricing::getSeatType, TheaterMoviePricing::getOverridePrice));

                List<TheaterSeatPricing> basicTicketPrices = theaterSeatPricingRepository.getTheaterBasicSeatPrices(theater.getId());


                Map<SeatType, BigDecimal> basicPrices = basicTicketPrices.stream()
                        .collect(Collectors.toMap(price -> price.getSeatType(), price -> price.getBasePrice()));

                for (Timing timing : timings) {
                    Show newShow = new Show();
                    newShow.setTheater(theater);
                    newShow.setTiming(timing);
                    newShow.setTheaterMovie(theaterMovie);
                    newShow.setShowDate(needToGenerateDay);
                    newShow.setIsActive(true);
                    Show savedShow = showsRepository.save(newShow);
                    List<ShowSeat> showSeats=new ArrayList<>();
                    for (Seat seat : seats) {
                        ShowSeat newShowSeat = new ShowSeat();
                        newShowSeat.setSeat(seat);
                        newShowSeat.setShow(savedShow);
                        newShowSeat.setStatus(ShowSeatStatus.AVAILABLE);
                        SeatType desiredSeatType = seat.getSeatType();
                        if (customPrices.containsKey(desiredSeatType)) {
                            newShowSeat.setFinalPrice(customPrices.get(desiredSeatType));
                        } else if (basicPrices.containsKey(desiredSeatType)) {
                            newShowSeat.setFinalPrice(basicPrices.get(desiredSeatType));
                        } else {
                            newShowSeat.setFinalPrice(BigDecimal.ZERO);
                        }

                        if (!BigDecimal.ZERO.equals(newShowSeat.getFinalPrice())) {
                            showSeats.add(newShowSeat);

                            showSeatsRepository.save(newShowSeat);
                        }
                    }
                    int batchSize = 50;
                    for (int i = 0; i < showSeats.size(); i += batchSize) {
                        int end = Math.min(i + batchSize, showSeats.size());
                        showSeatsRepository.saveAll(showSeats.subList(i, end));
                        showSeatsRepository.flush();
                    }

                }

                successTheaters.add(theaterMovie.getId());
                log.info("successfully created shows for the theaterMovie {}",theaterMovie.getId());
            } catch (Exception e) {
                log.error("error creating shows for the theaterMovie {} {}",theaterMovie.getId(),e.getMessage());
            }
        });
        return successTheaters;
    }


}

