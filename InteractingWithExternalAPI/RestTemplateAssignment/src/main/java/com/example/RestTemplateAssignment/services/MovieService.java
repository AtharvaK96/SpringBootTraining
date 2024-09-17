package com.example.RestTemplateAssignment.services;

import com.example.RestTemplateAssignment.models.Movie;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
//@AllArgsConstructor
public class MovieService {

    private final RestTemplate restTemplate;

    @Autowired
    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Movie> getAllMovies() {

        String api = "https://freetestapi.com/api/v1/movies";
        Movie[] movies = restTemplate.getForObject(api, Movie[].class);
        if (movies == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(movies);
    }

    public List<Movie> getMovieByGenre(String genre, String sortOrder) {
        String api = "https://freetestapi.com/api/v1/movies";
        Movie[] movies = restTemplate.getForObject(api, Movie[].class);
        if (movies == null) {
            return new ArrayList<>();
        }
        List<Movie> filteredByGenre;
        if (sortOrder.trim().equalsIgnoreCase("desc")) {
            filteredByGenre = Arrays.stream(movies)
                    .filter(movie -> movie.getGenre().contains(genre))
                    .sorted((m1, m2) -> m2.getTitle().compareTo(m1.getTitle()))
                    .toList();

        } else {
            filteredByGenre = Arrays.stream(movies)
                    .filter(movie -> movie.getGenre().contains(genre))
                    .sorted(Comparator.comparing(Movie::getTitle))
                    .toList();

        }
        return filteredByGenre;
    }


    public List<Movie> searchMovie(String name) {
        String api = "https://freetestapi.com/api/v1/movies?search=" + name;
        Movie[] searchedMovies = restTemplate.getForObject(api, Movie[].class);
        if (searchedMovies == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(searchedMovies);


    }

}
