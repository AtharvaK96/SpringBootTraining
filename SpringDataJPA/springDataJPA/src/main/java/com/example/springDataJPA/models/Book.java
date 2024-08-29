package com.example.springDataJPA.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @NotNull
    private String title;
    private Integer price;
    private String language;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private Category category;
}
