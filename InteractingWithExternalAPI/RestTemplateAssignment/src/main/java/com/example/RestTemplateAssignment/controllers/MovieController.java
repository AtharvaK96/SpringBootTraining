package com.example.RestTemplateAssignment.controllers;

import com.example.RestTemplateAssignment.models.Movie;
import com.example.RestTemplateAssignment.models.PaginationResponse;
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
    public ResponseEntity<PaginationResponse> getAllMovies(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                           @RequestParam(value = "size", defaultValue = "5", required = false) Integer size) {
        PaginationResponse allMovies = movieService.getAllMovies(page, size);
        return new ResponseEntity<>(allMovies, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<PaginationResponse> searchMovie(
            @RequestParam("query") String movieName,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "5", required = false) Integer size
    ) {
        PaginationResponse movies = movieService.searchMovie(movieName, page, size);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/genre-filter")
    public ResponseEntity<PaginationResponse> searchByGenre(
            @RequestParam("genre") String genre,
            @RequestParam("sortOrder") String sortOrder,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "5", required = false) Integer size

    ) {
        PaginationResponse movies = movieService.getMovieByGenre(genre, sortOrder, page, size);
        System.out.println(movies);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }


}
