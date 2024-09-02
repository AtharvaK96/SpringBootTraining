package com.example.springDataJPA.controllers;

import com.example.springDataJPA.dtos.book.BookDto;
import com.example.springDataJPA.models.Book;
import com.example.springDataJPA.services.BooksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BooksController {

    @Autowired
    BooksService booksService;


    @GetMapping("/all")
    public Page<BookDto> getAllBooks(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "", required = false) String sortBy
    ) {
        System.out.println(sortBy.isEmpty());
        return booksService.getAllBooks(pageNumber, pageSize, sortBy);
    }


    @PostMapping()
    public BookDto addBook(@Valid @RequestBody Book book) {
        return booksService.addBook(book);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@Valid @RequestBody Book book, @PathVariable("id") Integer id) {
        System.out.println(book);
        return booksService.updateBook(book, id);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Integer id) {
        return booksService.deleteBook(id);
    }

    @GetMapping("/search")
    public BookDto searchBookByName(@RequestParam("s") String name) {
        return booksService.findBookByName(name);
    }

    @GetMapping("/priceLessThan")
    public List<BookDto> searchBookByPriceLessThan(@RequestParam("price") Integer price) {
        return booksService.findBooksPriceLessThan(price);
    }

    @GetMapping("/getBook")
    public BookDto getSingleBook(@RequestParam("id") Integer id) {
        return booksService.getSingleBook(id);
    }


}
