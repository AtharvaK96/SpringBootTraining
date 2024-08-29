package com.example.springDataJPA.controllers;

import com.example.springDataJPA.models.Book;
import com.example.springDataJPA.services.BooksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BooksController {

    BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return booksService.getAllBooks();
    }
    @PostMapping()
    public Book addBook(@Valid @RequestBody Book book) {
        return booksService.addBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@Valid @RequestBody Book book, @PathVariable("id") Integer id) {
        System.out.println(book);
        return booksService.updateBook(book, id);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Integer id){
       return booksService.deleteBook(id);
    }
    @GetMapping("/search")
    public Book searchBookByName(@RequestParam("s") String name){
       return booksService.findBookByName(name);
    }

    @GetMapping("/priceLessThan")
    public List<Book> searchBookByPriceLessThan(@RequestParam("price") Integer price){
        return booksService.findBooksPriceLessThan(price);
    }


}
