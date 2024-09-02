package com.example.springDataJPA.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String nationality;

    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();


}
