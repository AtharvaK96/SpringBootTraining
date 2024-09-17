package com.Assignment.Eight.assignment_eight.controller;

import com.Assignment.Eight.assignment_eight.model.Movie;
import com.Assignment.Eight.assignment_eight.service.HttpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
//@RequestMapping("api/v1")
public class HttpController {

    private final HttpService httpService;
    public HttpController(HttpService httpService){
        this.httpService=httpService;
    }
    @GetMapping("/movie")
    public Movie getMovies(@RequestParam int id){
        return httpService.getMovies(id);
    }
    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        return httpService.getAllMovies();
    }
    @GetMapping("getMovieByGenre")
    public List<Movie> getMoviesByGenre(){
        return httpService.getMovieByGenre();
    }
    @GetMapping("orderByDirector")
    public List<Movie> getOrderByDirector(@RequestParam String sequence){
        return httpService.sortMovieByDirector(sequence);
    }

    }

