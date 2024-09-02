package com.example.springDataJPA.controllers;

import com.example.springDataJPA.dtos.author.AuthorDto;
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
    public AuthorDto getAuthor(@PathVariable("id") Integer id) {
        return authorService.getBooksByAuthorId(id);
    }

}
