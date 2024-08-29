package com.example.assignment_four.service;

import com.example.assignment_four.model.Author;
import com.example.assignment_four.model.Book;
import com.example.assignment_four.model.Category;
import com.example.assignment_four.repository.BookRepository;
import com.sun.source.tree.LambdaExpressionTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> displayBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(int id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Optional<List<Book>> findBooksAbovePrice(double price) {
        return bookRepository.findByPriceGreaterThan(price);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(int id, Book book) {
        Author author = new Author(book.getAuthor().getId(),book.getAuthor().getName());
        Category category = new Category(book.getCategory().getId(), book.getCategory().getName());
        return bookRepository.save(new Book(id, book.getTitle(), book.getPrice(), author, category));
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
