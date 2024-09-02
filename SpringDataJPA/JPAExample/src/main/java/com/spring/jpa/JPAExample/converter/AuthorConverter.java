package com.spring.jpa.JPAExample.converter;

import com.spring.jpa.JPAExample.DTO.AuthorDTO;
import com.spring.jpa.JPAExample.model.Author;
import com.spring.jpa.JPAExample.model.Book;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthorConverter {
    public AuthorDTO toAuthorDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorId(author.getId());
        authorDTO.setAuthorName(author.getName());
        authorDTO.setBookTitle(author.getBook().stream().map(Book::getTitle).collect(Collectors.toList()));
        return authorDTO;
    }
}
