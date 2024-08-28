package h2JDBCTraining.JdbcTempleteTraining.controller;

import h2JDBCTraining.JdbcTempleteTraining.model.Book;
import h2JDBCTraining.JdbcTempleteTraining.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

}
