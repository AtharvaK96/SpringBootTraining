package com.example.RestTemplateAssignment.services;

import com.example.RestTemplateAssignment.models.Movie;
import com.example.RestTemplateAssignment.models.PaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import java.util.stream.Stream;

@Service
public class MovieService {

    private final RestTemplate restTemplate;

    @Autowired
    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PaginationResponse getAllMovies(int page, int size) {

        String api = "https://freetestapi.com/api/v1/movies";
        Movie[] movies = restTemplate.getForObject(api, Movie[].class);
        if (movies == null) {
            return paginateMovies(new ArrayList<>(),page,size);
        }
        return paginateMovies(Arrays.asList(movies),page,size) ;
    }

    public PaginationResponse getMovieByGenre(String genre, String sortOrder, int page, int size) {
        List<String> genreList = Arrays.stream(genre.split(",")).toList();
        String api = "https://freetestapi.com/api/v1/movies";
        Movie[] movies = restTemplate.getForObject(api, Movie[].class);
        if (movies == null) {
            return paginateMovies(new ArrayList<>(),page,size);
        }
        Stream<Movie> filteredByGenre = Arrays.stream(movies)
                .filter(movie -> new HashSet<>(movie.getGenre()).containsAll(genreList));
        List<Movie> sortedList;

        if (sortOrder.trim().equalsIgnoreCase("desc")) {
            sortedList = filteredByGenre
                    .sorted((m1, m2) -> m2.getTitle().compareTo(m1.getTitle()))
                    .toList();

        } else {
            sortedList = filteredByGenre
                    .sorted(Comparator.comparing(Movie::getTitle))
                    .toList();

        }


        return paginateMovies(sortedList, page, size);
    }


    public PaginationResponse searchMovie(String name, int page, int size) {
        String api = "https://freetestapi.com/api/v1/movies?search=" + name;
        Movie[] searchedMovies = restTemplate.getForObject(api, Movie[].class);
        if (searchedMovies == null) {
            return paginateMovies(new ArrayList<>(),page,size);
        }
        return paginateMovies(Arrays.asList(searchedMovies),page, size) ;


    }

    private PaginationResponse paginateMovies(List<Movie> movieList, int page, int size) {
        int start = Math.min(page * size, movieList.size());
        int end = Math.min(start + size, movieList.size());
        return PaginationResponse.builder()
                .movies( movieList.subList(start, end))
                .lastPage(end >= movieList.size())
                .totalPages((movieList.size() + size - 1) / size)
                .currentPage(page)
                .build();
    }



}
