package com.naveen.movieticketplatform.mapper;


import com.naveen.movieticketplatform.dto.MovieCreationRequest;
import com.naveen.movieticketplatform.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target="languages",ignore = true)
    @Mapping(target="genres",ignore=true)
    @Mapping(target="formats",ignore = true)
    @Mapping(target = "censorRating", ignore = true)
    Movie toMovieEntity(MovieCreationRequest movieCreationRequest);
}
