package com.example.external_api_assignment.controller;

import com.example.external_api_assignment.model.Movie;
import com.example.external_api_assignment.service.HttpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class RequestController {
    private final HttpService httpService;

    public RequestController(HttpService httpService) {
        this.httpService = httpService;
    }

    @GetMapping("/getMovie")
    public Movie getMovie(@RequestParam int id) {
        return httpService.getMovie(id);
    }

    @GetMapping("/getAllMovies")
    public List<Movie> getAllMovies(int page, int size) {
        return httpService.getAllMovies(page, size);
    }

    @GetMapping("/getMoviesByGenre")
    public List<Movie> getMoviesByGenre(@RequestParam List<String> genres) {
        return httpService.getMoviesByGenre(genres);
    }

    @GetMapping("/orderByRating")
    public List<Movie> orderByRating(@RequestParam(defaultValue = "ascending") String order) {
        return httpService.orderByRating(order);
    }
}
