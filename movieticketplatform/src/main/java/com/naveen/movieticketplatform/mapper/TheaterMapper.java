package com.naveen.movieticketplatform.mapper;


import com.naveen.movieticketplatform.dto.TheaterRequestDto;
import com.naveen.movieticketplatform.entity.Theater;
import org.mapstruct.*;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface TheaterMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    Theater toTheaterEntity(TheaterRequestDto dto);

}
