package com.JpaAssignment.jpa_assignment.repository;

import com.JpaAssignment.jpa_assignment.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    List<Book> findByPriceLessThan(double price);
    List<Book> findByPriceGreaterThan(double price);
}
