package com.example.RestTemplateAssignment.controllers;

import com.example.RestTemplateAssignment.models.Movie;
import com.example.RestTemplateAssignment.services.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        List<Movie> allMovies = movieService.getAllMovies();
        return new ResponseEntity<>(allMovies, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovie(@RequestParam("query") String movieName){
        List<Movie> movies = movieService.searchMovie(movieName);
        System.out.println(movies);
        return new ResponseEntity<>(movies,HttpStatus.OK);
    }
    @GetMapping("/genre-filter")
    public ResponseEntity<List<Movie>> searchByGenre(@RequestParam("genre") String genre,
                                                     @RequestParam("sortOrder") String sortOrder){
        List<Movie> movies = movieService.getMovieByGenre(genre,sortOrder);
        System.out.println(movies);
        return new ResponseEntity<>(movies,HttpStatus.OK);
    }


}
