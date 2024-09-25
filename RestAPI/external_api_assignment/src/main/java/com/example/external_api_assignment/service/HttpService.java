package com.example.external_api_assignment.service;

import com.example.external_api_assignment.exception.MovieNotFoundException;
import com.example.external_api_assignment.model.Movie;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class HttpService {
    RestTemplate restTemplate=new RestTemplate();
    public Movie getMovie(int id) {
        Movie movie=restTemplate.getForObject("https://freetestapi.com/api/v1/movies/"+id, Movie.class);
        return movie;
    }

    public List<Movie> getAllMovies(int page, int size) {
        Movie[] movies=restTemplate.getForObject("https://freetestapi.com/api/v1/movies", Movie[].class);
        if(movies!=null) {
            int totalMovies = movies.length;
            int startIndex = page * size;
            int endIndex = Math.min(startIndex + size, totalMovies);
            return List.of(movies).subList(startIndex, endIndex);
        }
        throw new MovieNotFoundException("Movies not found");
    }

    public List<Movie> getMoviesByGenre(List<String> genres) {
        Movie[] movies = restTemplate.getForObject("https://freetestapi.com/api/v1/movies", Movie[].class);
        List<Movie> movieList= Stream.of(movies)
                .filter(movie -> genres.stream().allMatch(genre -> movie.getGenre().contains(genre))).toList();
        if(movieList.isEmpty()) {
            throw new MovieNotFoundException("Movies not found with genre " + genres);
        }
        return movieList;
    }

    public List<Movie> orderByRating(String order) {
        Movie[] movies = restTemplate.getForObject("https://freetestapi.com/api/v1/movies", Movie[].class);
        if(order.equalsIgnoreCase("ascending")) {
            return Stream.of(movies).sorted(Comparator.comparing(Movie::getRating)).toList();
        }
        else if(order.equalsIgnoreCase("descending")) {
            return List.of(movies).stream().sorted(Comparator.comparing(Movie::getRating).reversed()).toList();
        }
        return List.of(movies);
    }
}

