package com.spring.jpa.JPAExample.controller;

import com.spring.jpa.JPAExample.DTO.AuthorDTO;
import com.spring.jpa.JPAExample.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/getAllAuthors")
    public Page<AuthorDTO> getAllAuthors(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return authorService.getAllAuthors(pageNumber, pageSize);
    }

    @GetMapping("/getAuthor")
    public <Optional> AuthorDTO getAuthor(@RequestParam int id) {
        return authorService.getAuthor(id);
    }
}
