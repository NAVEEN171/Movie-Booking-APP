package com.naveen.movieticketplatform.mapper;


import com.naveen.movieticketplatform.dto.MovieCreationRequest;
import com.naveen.movieticketplatform.entity.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface MovieMapper {
    Movie ToMovieEntity(MovieCreationRequest movieCreationRequest);
}
