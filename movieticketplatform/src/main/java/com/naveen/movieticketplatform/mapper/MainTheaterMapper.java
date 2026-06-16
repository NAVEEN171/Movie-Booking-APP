package com.naveen.movieticketplatform.mapper;


import com.naveen.movieticketplatform.dto.TheaterCreationRequestDto;
import com.naveen.movieticketplatform.dto.TheaterRequestDto;
import com.naveen.movieticketplatform.entity.MainTheater;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MainTheaterMapper {
    @Mapping(target="id",ignore = true)
    @Mapping(target="isActive",ignore = true)
    MainTheater toMainTheaterEntity(TheaterCreationRequestDto theaterCreationRequestDto);
}
