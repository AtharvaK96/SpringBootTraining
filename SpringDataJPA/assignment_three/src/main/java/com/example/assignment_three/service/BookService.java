package com.example.assignment_three.service;

import com.example.assignment_three.module.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.assignment_three.repository.BookRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class BookService {
    int i = 5;
    @Autowired
    BookRepository bookRepository;

    public List<Book> displayBooks() {
        return bookRepository.displayBooks();
    }

    public String addBook(Book book) {
        i++;
        return bookRepository.addBook(new Book(i, book.getTitle(), book.getPrice()));
    }

    public String updateBook(int id, Book book) {
        return bookRepository.updateBook(id, book);
    }

    public String deleteBook(@PathVariable int id) {
        return bookRepository.deleteBook(id);
    }
}
