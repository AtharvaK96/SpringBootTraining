package com.Assignment.Eight.assignment_eight.service;

import com.Assignment.Eight.assignment_eight.model.Movie;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HttpService {
    public Movie getMovies(@RequestParam int id) {
        RestTemplate template = new RestTemplate();
        Movie myMovie = template.getForObject("https://freetestapi.com/api/v1/movies/" + id, Movie.class);

        return myMovie;
    }

    public List<Movie> getAllMovies() {
        RestTemplate template = new RestTemplate();
        Movie[] myMovie = template.getForObject("https://freetestapi.com/api/v1/movies", Movie[].class);
        return List.of(myMovie);
    }

    public List<Movie> getMovieByGenre() {
        RestTemplate template = new RestTemplate();
        Movie[] myMovie = template.getForObject("https://freetestapi.com/api/v1/movies", Movie[].class);
        if (myMovie != null) {
            return List.of(myMovie).stream()
                    .filter(n -> n.getGenre().contains("Thriller"))
                    .collect(Collectors.toList());
        }
        return List.of();
    }


    public List<Movie> sortMovieByDirector(String sequence) {
        RestTemplate template = new RestTemplate();
        Movie[] myMovie = template.getForObject("https://freetestapi.com/api/v1/movies", Movie[].class);
        if(sequence.equalsIgnoreCase("ascending")) {
            return List.of(myMovie).stream().sorted(Comparator.comparing(Movie::getDirector)).toList();
        }
        else if(sequence.equalsIgnoreCase("descending")) {
            return List.of(myMovie).stream().sorted(Comparator.comparing(Movie::getDirector).reversed()).toList();
        }
        else {
            return List.of(myMovie);
        }
    }

    }

