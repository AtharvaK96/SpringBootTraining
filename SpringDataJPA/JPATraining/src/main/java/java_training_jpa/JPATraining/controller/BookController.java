package java_training_jpa.JPATraining.controller;


import java_training_jpa.JPATraining.DTO.BookDto;
import java_training_jpa.JPATraining.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/getAll")
    public Page<BookDto> getAllBooks(@RequestParam("pageNumber")int pageNumber,
                                     @RequestParam("pageSize") int pageSize) {
        return bookService.getAllBooks(pageNumber,pageSize);
    }
}