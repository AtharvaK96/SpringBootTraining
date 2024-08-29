package com.Assignment.Four.assignment_four_new.repository;

import com.Assignment.Four.assignment_four_new.model.Book;
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
