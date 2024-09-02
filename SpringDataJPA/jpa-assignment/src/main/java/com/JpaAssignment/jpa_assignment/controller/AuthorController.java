package com.JpaAssignment.jpa_assignment.controller;

import com.JpaAssignment.jpa_assignment.DTO.AuthorDto;
import com.JpaAssignment.jpa_assignment.model.Author;
import com.JpaAssignment.jpa_assignment.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @GetMapping("/Name")
    Optional<Author> findByAuthorName(@RequestParam String name){
        return authorService.findByAuthorName(name);
    }
    @GetMapping("/Sorted")
    public Page<AuthorDto> getAllAuthors(@RequestParam("pageNumber")int pageNumber,
                                         @RequestParam("pageSize") int pageSize) {
        return authorService.getAllAuthors(pageNumber,pageSize);
    }

}
