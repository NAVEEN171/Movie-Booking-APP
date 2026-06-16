package com.naveen.movieticketplatform.service;


import com.naveen.movieticketplatform.dto.MovieCreationRequest;
import com.naveen.movieticketplatform.dto.TimingsRequestDto;
import com.naveen.movieticketplatform.entity.*;
import com.naveen.movieticketplatform.mapper.MovieMapper;
import com.naveen.movieticketplatform.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieLanguageRepository movieLanguageRepository;
    private final MovieFormatsRepository movieFormatsRepository;
    private final MovieGenreRepository movieGenreRepository;
    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;

    private final TheaterRepository theaterRepository;
    private final TimingsService timingsService;
    private final CensorRatingRepository censorRatingRepository;


    public List<Movie> createMovie(List<MovieCreationRequest> movieRequest) {
        List<Movie> savedMovies=new ArrayList<>();

        for(MovieCreationRequest movie:movieRequest){
            List<Language> languages = movieLanguageRepository.findAllById(movie.getLanguages());
            List<Format> formats = movieFormatsRepository.findAllById(movie.getFormats());
            List<Genre> genres = movieGenreRepository.findAllById(movie.getGenres());
            Optional<Theater> theater=theaterRepository.findById(movie.getTheaterId());
            CensorRating censorRating = censorRatingRepository.findById(movie.getCensorRatingId())
                    .orElseThrow(() -> new RuntimeException("CensorRating not found"));
            if(languages.isEmpty()) {
                throw new RuntimeException("Invalid language IDs provided: " + movie.getLanguages());
            }
            if(formats.isEmpty()) {
                throw new RuntimeException("Invalid format IDs provided: " + movie.getFormats());
            }
            if(genres.isEmpty()) {
                throw new RuntimeException("Invalid genre IDs provided: " + movie.getGenres());
            }
            if(theater.isEmpty()) {
                throw new RuntimeException("Theater not found with ID: " + movie.getTheaterId());
            }

            Movie newMovie=movieMapper.toMovieEntity(movie);
            newMovie.setLanguages(new HashSet<>(languages));
            newMovie.setGenres(new HashSet<>(genres));
            newMovie.setFormats(new HashSet<>(formats));
            newMovie.setTheater(theater.get());
            newMovie.setCensorRating(censorRating);
            Movie savedMovie=movieRepository.save(newMovie);



            TimingsRequestDto timingsRequest=movie.getTimings();
            timingsRequest.setMovieId(savedMovie.getId());

            TimingMain savedTimingMain =timingsService.createTheaterTimings(timingsRequest);
            savedMovie.setTimingsMain(savedTimingMain);
            Movie savedTimingsMovie=movieRepository.save(savedMovie);
            savedMovies.add(savedTimingsMovie);


        }

        return savedMovies;
    }
}
