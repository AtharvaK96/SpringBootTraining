package com.example.assignment_three.controller;

import com.example.assignment_three.module.Book;
import com.example.assignment_three.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/display")
    public List<Book> displayBooks() {
        return bookService.displayBooks();
    }

    @PostMapping("/add")
    public String addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/update")
    public String updateBook(@RequestParam int id, @RequestBody Book book) {
        return  bookService.updateBook(id, book);
    }

    @DeleteMapping("/delete")
    public String deleteBook(@RequestParam int id) {
        return bookService.deleteBook(id);
    }
}
