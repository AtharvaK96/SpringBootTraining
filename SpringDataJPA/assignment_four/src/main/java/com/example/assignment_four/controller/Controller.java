package com.example.assignment_four.controller;

import com.example.assignment_four.model.Book;
import com.example.assignment_four.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class Controller {
    @Autowired
    BookService bookService;

    @GetMapping("/displayAllBooks")
    public List<Book> displayBooks() {
        return bookService.displayBooks();
    }

    @GetMapping("/findBookById")
    public Optional<Book> findBookById(@RequestParam int id) {
        return  bookService.findBookById(id);
    }

    @GetMapping("/findBookByTitle")
    public Optional<Book> findBookByTitle(@RequestParam String title) {
        return  bookService.findBookByTitle(title);
    }

    @GetMapping("/findBooksByPriceAbove")
    public Optional<List<Book>> findBooksAbovePrice(@RequestParam double price) {
        return  bookService.findBooksAbovePrice(price);
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/update")
    public Book updateBook(@RequestParam int id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/delete")
    public void deleteBook(@RequestParam int id) {
        bookService.deleteBook(id);
    }
}
