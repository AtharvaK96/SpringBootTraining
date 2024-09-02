package com.JpaAssignment.jpa_assignment.controller;

import com.JpaAssignment.jpa_assignment.DTO.BookDto;
import com.JpaAssignment.jpa_assignment.model.Book;
import com.JpaAssignment.jpa_assignment.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    BookService bookService;
//    @GetMapping("/getAllBooks")
//    public List<BookDto> displayBooks(){
//        return bookService.displayBooks();
//
//    }
@GetMapping("/getAll")
public Page<BookDto> getAllBooks(@RequestParam("pageNumber")int pageNumber,
                                 @RequestParam("pageSize") int pageSize) {
    return bookService.getAllBooks(pageNumber,pageSize);
}
    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }
    @PutMapping("/update/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book){
        return bookService.updateBook(id, book);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        bookService.delete(id);
    }
    @GetMapping("/title/{title}")
    public Optional<Book> getBookByTitle(@PathVariable String title) {
        return bookService.findBookByTitle(title);
    }
    @GetMapping("/price")
    public List<Book> getBooksLessThan500() {
        return bookService.findBooksLessThan500();
    }
    @GetMapping("/price/greater")
    public List<Book> getBooksGreaterThan500() {
        return bookService.findBooksGreaterThan500();
    }
}
