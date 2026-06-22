package com.naveen.movieticketplatform.service;

import com.naveen.movieticketplatform.dto.MovieCreationRequest;
import com.naveen.movieticketplatform.dto.MovieListResponse;
import com.naveen.movieticketplatform.dto.TimingsRequestDto;
import com.naveen.movieticketplatform.entity.CensorRating;
import com.naveen.movieticketplatform.entity.Format;
import com.naveen.movieticketplatform.entity.Genre;
import com.naveen.movieticketplatform.entity.Language;
import com.naveen.movieticketplatform.entity.Movie;
import com.naveen.movieticketplatform.entity.Theater;
import com.naveen.movieticketplatform.entity.TimingMain;
import com.naveen.movieticketplatform.mapper.MovieMapper;
import com.naveen.movieticketplatform.repository.CensorRatingRepository;
import com.naveen.movieticketplatform.repository.MovieFormatsRepository;
import com.naveen.movieticketplatform.repository.MovieGenreRepository;
import com.naveen.movieticketplatform.repository.MovieLanguageRepository;
import com.naveen.movieticketplatform.repository.MovieRepository;
import com.naveen.movieticketplatform.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieLanguageRepository movieLanguageRepository;
    private final MovieFormatsRepository movieFormatsRepository;
    private final MovieGenreRepository movieGenreRepository;
    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;
    private final CensorRatingRepository censorRatingRepository;

    public List<Movie> createMovie(List<MovieCreationRequest> movieRequest) {
        List<Movie> savedMovies = new ArrayList<>();

        for (MovieCreationRequest movie : movieRequest) {
            List<Language> languages = movieLanguageRepository.findAllById(movie.getLanguages());
            List<Format> formats = movieFormatsRepository.findAllById(movie.getFormats());
            List<Genre> genres = movieGenreRepository.findAllById(movie.getGenres());

            CensorRating censorRating = censorRatingRepository.findById(movie.getCensorRatingId())
                    .orElseThrow(() -> new NoSuchElementException("CensorRating not found"));

            if (languages.isEmpty()) {
                throw new IllegalArgumentException("Invalid language IDs provided: " + movie.getLanguages());
            }
            if (formats.isEmpty()) {
                throw new IllegalArgumentException("Invalid format IDs provided: " + movie.getFormats());
            }
            if (genres.isEmpty()) {
                throw new IllegalArgumentException("Invalid genre IDs provided: " + movie.getGenres());
            }


            Movie newMovie = movieMapper.toMovieEntity(movie);
            newMovie.setLanguages(new HashSet<>(languages));
            newMovie.setGenres(new HashSet<>(genres));
            newMovie.setFormats(new HashSet<>(formats));
            newMovie.setCensorRating(censorRating);
            Movie savedMovie = movieRepository.save(newMovie);
            savedMovies.add(savedMovie);

        }

        return savedMovies;
    }

    public Movie getMovie(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new NoSuchElementException("Movie not found with ID: " + movieId));
    }

    public List<MovieListResponse> getMoviesInALocation(String location){
        List<MovieListResponse> movies= movieRepository.getMoviesInALocation(location);
        if(movies.isEmpty()){
            throw new NoSuchElementException("no movies found in "+location);
        }
        return movies;
    }
}
