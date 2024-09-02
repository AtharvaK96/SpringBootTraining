package com.example.springDataJPA.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Entity
@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("book_id")
    private Integer id;
    @NotNull
    private String title;
    private Integer price;
    private String language;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull
//    @JsonBackReference
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private Category category;
}
