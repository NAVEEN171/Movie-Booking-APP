package com.naveen.movieticketplatform.service;

import com.naveen.movieticketplatform.dto.TheaterPricingDto;
import com.naveen.movieticketplatform.dto.TheaterRequestDto;
import com.naveen.movieticketplatform.entity.Seat;
import com.naveen.movieticketplatform.entity.Theater;
import com.naveen.movieticketplatform.entity.TheaterSeatPricing;
import com.naveen.movieticketplatform.enums.SeatType;
import com.naveen.movieticketplatform.mapper.TheaterMapper;
import com.naveen.movieticketplatform.repository.SeatRepository;
import com.naveen.movieticketplatform.repository.TheaterRepository;
import com.naveen.movieticketplatform.repository.TheaterSeatPricingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TheaterService {

    private final TheaterRepository theaterRepository;
    private final SeatRepository seatRepository;
    private final TheaterMapper theaterMapper;
    private final TheaterSeatPricingRepository theaterSeatPricingRepository;

    @Transactional
    public Theater createTheater(TheaterRequestDto theater){

        Theater newTheater=theaterMapper.toTheaterEntity(theater);
        newTheater.setIsActive(true);
        newTheater.setCreatedBy(1L);
        Theater savedTheater=theaterRepository.save(newTheater);
        for(TheaterPricingDto basePricing:theater.getTheaterBasePricing()){
            TheaterSeatPricing TheaterSeatPrice=new TheaterSeatPricing();
            TheaterSeatPrice.setBasePrice(basePricing.getPrice());
            TheaterSeatPrice.setSeatType(basePricing.getSeatType());
            TheaterSeatPrice.setTheater(savedTheater);
            TheaterSeatPrice.setIsActive(true);
            TheaterSeatPrice.setCreatedBy(1L);
            theaterSeatPricingRepository.save(TheaterSeatPrice);
        }
        List<Seat> seats=generateSeatOfTheater(savedTheater,theater.getReclinerRowIndexes(),theater.getRegularRowIndexes());
        if(!seats.isEmpty()){
            int batchSize = 50;
            for (int i = 0; i < seats.size(); i += batchSize) {
                int end = Math.min(i + batchSize, seats.size());
                seatRepository.saveAll(seats.subList(i, end));
                seatRepository.flush();
            }
        }

        return  savedTheater;
    }

    public List<Seat> generateSeatOfTheater(Theater theater, Set<Integer> reclinerRowIndexes,Set<Integer> regularRowIndexes){
        Integer rows = theater.getNoOfRows();
        Integer noOfRowsInRow=theater.getNoOfRowsInARow();
        Integer noOfColumnsInRow= theater.getNoOfColumnsInARow();
        List<Seat> theaterSeats=new ArrayList<>();
        for(int row=0;row<noOfRowsInRow;row++){
                   for(int seatNo=0;seatNo<rows*noOfColumnsInRow;seatNo++){
                       Seat seat=new Seat();
                       seat.setTheater(theater);
                       char seatRow=(char)('A'+row);
                       String seatNum= seatRow+String.valueOf((seatNo+1));
                       seat.setSeatNo(seatNum);
                       seat.setCreatedBy(1L);
                       seat.setSeatType(determineSeatType(row,reclinerRowIndexes,regularRowIndexes));
                       seat.setIsActive(true);
                       theaterSeats.add(seat);
                   }
        }
        return theaterSeats;
    }

    public SeatType determineSeatType(Integer index ,Set<Integer> reclinerRowIndexes,Set<Integer> regularRowIndexes){

        if(reclinerRowIndexes.contains(index)){
            return SeatType.RECLINER;
        }
        else if(regularRowIndexes.contains(index)){
            return SeatType.REGULAR;
        }
        else{
            return SeatType.PREMIUM;
        }

    }

}
