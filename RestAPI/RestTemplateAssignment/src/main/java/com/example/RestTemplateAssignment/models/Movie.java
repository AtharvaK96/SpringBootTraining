package com.example.RestTemplateAssignment.models;

import lombok.Data;

import java.util.List;

@Data
public class Movie {

    private int id;

    private String title;

    private int year;

    private List<String> genre;
}
