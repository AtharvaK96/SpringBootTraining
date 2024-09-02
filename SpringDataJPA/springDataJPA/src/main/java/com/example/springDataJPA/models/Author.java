package com.example.springDataJPA.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String nationality;

    @OneToMany(mappedBy = "author")
//    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
//    @JsonManagedReference
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        book.setAuthor(this);
        books.add(book);
    }
    public void removeBook( Book book){
        book.setAuthor(null);
        books.remove(book);
    }

}
