package com.spring.jpa.JPAExample.controller;

import com.spring.jpa.JPAExample.DTO.BookDTO;
import com.spring.jpa.JPAExample.model.Book;
import com.spring.jpa.JPAExample.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/getAllBooks")
    public Page<BookDTO> getAllBooks(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return bookService.getAllBooks(pageNumber, pageSize);
    }

    @GetMapping("/getBookById")
    public Optional<Book> getBookById(@RequestParam int id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/getBookByTitle")
    public Optional<Book> getBookByTitle(@RequestParam String title) {
        return bookService.getBookByTitle(title);
    }

    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/updateBook")
    public Book updateBook(@RequestParam int id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/deleteBook")
    public void deleteBook(@RequestParam int id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/getBooksWithPriceAbove")
    public List<Book> getBooksWithPriceAbove(@RequestParam int price) {
        return bookService.getBooksWithPriceAbove(price);
    }
}
