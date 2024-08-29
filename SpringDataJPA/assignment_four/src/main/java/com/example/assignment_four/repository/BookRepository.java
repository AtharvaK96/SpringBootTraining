package com.example.assignment_four.repository;

import com.example.assignment_four.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByTitle(String title);
    Optional<List<Book>> findByPriceGreaterThan(double price);
}
