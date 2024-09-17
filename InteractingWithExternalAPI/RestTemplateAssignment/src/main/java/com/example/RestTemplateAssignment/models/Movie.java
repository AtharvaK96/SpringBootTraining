package com.example.RestTemplateAssignment.models;

import lombok.Data;

import java.util.List;

@Data
public class Movie {
    private int id;
    private String title;
    private int year;
    private List<String> genre;
    private double rating;
    private String director;
    private List<String> actors;
    private String plot;
    private String poster;
    private String trailer;
    private int runtime;
    private String awards;
    private String country;
    private String language;
    private String boxOffice;
    private String production;
    private String website;
}
