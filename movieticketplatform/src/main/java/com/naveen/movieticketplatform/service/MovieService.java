package com.naveen.movieticketplatform.service;


import com.naveen.movieticketplatform.dto.MovieCreationRequest;
import com.naveen.movieticketplatform.entity.*;
import com.naveen.movieticketplatform.repository.MovieFormatsRepository;
import com.naveen.movieticketplatform.repository.MovieGenreRepository;
import com.naveen.movieticketplatform.repository.MovieLanguageRepository;
import com.naveen.movieticketplatform.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieLanguageRepository movieLanguageRepository;
    private final MovieFormatsRepository movieFormatsRepository;
    private final MovieGenreRepository movieGenreRepository;

    private final TheaterRepository theaterRepository;

    public Movie createMovie(MovieCreationRequest request) {
        List<Language> languages = movieLanguageRepository.findAllById(request.getLanguages());
        List<Format> formats = movieFormatsRepository.findAllById(request.getFormats());
        List<Genre> genres = movieGenreRepository.findAllById(request.getGenres());
        Optional<Theater> theater=theaterRepository.findById(request.getTheaterId());
        if(languages.size()==0 || formats.size()==0 || genres.size()==0 || theater.isEmpty()){
            //Error response should be returned here
            return null;
        }
        return null;
    }
}
