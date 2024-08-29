package com.example.springDataJPA.repository;

import com.example.springDataJPA.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
//    Book findByName
    Book findByTitle(String title);
    List<Book> findByPriceLessThan(Integer price);


}
