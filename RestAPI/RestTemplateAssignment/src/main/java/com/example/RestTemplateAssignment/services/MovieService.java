package com.example.RestTemplateAssignment.services;

import com.example.RestTemplateAssignment.models.Movie;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    RestTemplate restTemplate = new RestTemplate();

    public Movie getMovie(int id) {
        return restTemplate.getForObject("https://freetestapi.com/api/v1/movies/" + id, Movie.class);
    }

    public List<Movie> getMovies() {
        Movie[] moviesArray = restTemplate.getForObject("https://freetestapi.com/api/v1/movies", Movie[].class);
        return List.of(moviesArray);
    }

    public List<Movie> searchMoviesByGenre(String genre) {
        List<Movie> movies = getMovies();
        return movies.stream().filter(movie -> movie.getGenre().contains(genre)).toList();
    }

    public List<Movie> searchMoviesByGenreAndSort(String genre, String sortOrder) {
        List<Movie> filteredMovies = searchMoviesByGenre(genre);

        Comparator<Movie> movieComparator = Comparator.comparingInt(Movie::getId);

        if ("asc".equalsIgnoreCase(sortOrder)) {
            return filteredMovies.stream()
                    .sorted(movieComparator)
                    .collect(Collectors.toList());
        } else if ("desc".equalsIgnoreCase(sortOrder)) {
            return filteredMovies.stream()
                    .sorted(movieComparator.reversed())
                    .collect(Collectors.toList());
        } else {
            return filteredMovies;
        }
    }
}
