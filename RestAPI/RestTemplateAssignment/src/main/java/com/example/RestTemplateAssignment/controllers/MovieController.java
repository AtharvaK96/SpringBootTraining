package com.example.RestTemplateAssignment.controllers;

import com.example.RestTemplateAssignment.models.Movie;
import com.example.RestTemplateAssignment.services.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable int id){
        return movieService.getMovie(id);
    }

    @GetMapping
    public List<Movie> getMovies(){
        return movieService.getMovies();
    }

    @GetMapping("/searchMoviesByGenre")
    public List<Movie> searchMoviesByGenre(@RequestParam String genre) {
        return movieService.searchMoviesByGenre(genre);
    }

    @GetMapping("/searchMoviesByGenreAndSort")
    public List<Movie> MoviesByGenreAndSort(@RequestParam String genre, @RequestParam String sortOrder){
        return movieService.searchMoviesByGenreAndSort(genre, sortOrder);
    }
}
