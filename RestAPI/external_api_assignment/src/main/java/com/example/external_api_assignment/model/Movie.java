package com.example.external_api_assignment.model;

import lombok.Data;

import java.util.List;

@Data
public class Movie {
    private int id;
    private String title;
    private int year;
    private List<String> genre;
    private int rating;
}
