package com.JpaAssignment.jpa_assignment.controller;

import com.JpaAssignment.jpa_assignment.DTO.AuthorDto;
import com.JpaAssignment.jpa_assignment.model.Author;
import com.JpaAssignment.jpa_assignment.model.Book;
import org.springframework.stereotype.Service;

@Service
public class AuthorConverter {
    public AuthorDto convertsAuthorToDto(Author author){
        AuthorDto authorDto=new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setAuthorName(author.getName());
        authorDto.setBookTitle(author.getBook().stream().map(Book::getTitle).toList());
        return authorDto;
    }
}
