package com.example.springDataJPA.controllers;

import com.example.springDataJPA.dtos.author.AuthorBooksRespnseDto;
import com.example.springDataJPA.models.Author;
import com.example.springDataJPA.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public AuthorBooksRespnseDto getAuthor(@PathVariable("id") Integer id){
        return authorService.getBooksByAuthorId(id);
    }

    @GetMapping("/testingAuthor/{id}")
    public Author getAuthorTesting(@PathVariable("id") Integer id){
        return authorService.getBooksByAuthorTestingId(id);
    }
}
