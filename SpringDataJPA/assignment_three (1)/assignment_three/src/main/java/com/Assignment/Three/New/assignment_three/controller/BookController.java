package com.Assignment.Three.New.assignment_three.controller;

import com.Assignment.Three.New.assignment_three.model.Book;
import com.Assignment.Three.New.assignment_three.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks(){
        return bookService.fetchAllBooks();
    }

    @PostMapping("/addBook")
    public String addBook(@RequestBody Book book){
        return bookService.insertBook(book);
    }
    @PutMapping("/updateBook/{id}")
    public String updateBook(@PathVariable int id,@RequestBody Book book){
        return bookService.updateBook(id, book);
    }
    @DeleteMapping("/delete/{id}")
    public String recordDeleted(@PathVariable int id){
        return bookService.recordDeleted(id);
    }

}

