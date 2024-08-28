package com.example.JDBCExample.service;

import com.example.JDBCExample.model.Book;
import com.example.JDBCExample.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public String addBook(Book book) {
        return bookRepository.addBook(book);
    }

    public String updateBook(int id, Book book) {
        return bookRepository.updateBook(id, book);
    }

    public String deleteBook(int id) {
        return bookRepository.deleteBook(id);
    }
}
