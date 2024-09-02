package com.example.assignment_four.controller;

import com.example.assignment_four.dto.AuthorDTO;
import com.example.assignment_four.dto.BookDTO;
import com.example.assignment_four.model.Author;
import com.example.assignment_four.model.Book;
import com.example.assignment_four.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/displayAllAuthors")
    public Page<AuthorDTO> displayAuthors(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return authorService.displayAuthors(pageNumber, pageSize);
    }

    @GetMapping("/findAuthor")
    public Optional<Author> findAuthor(@RequestParam String name) {
        return  authorService.findBooksByAuthor(name);
    }
}
