package com.example.assignment_four.converter;

import com.example.assignment_four.dto.AuthorDTO;
import com.example.assignment_four.model.Author;
import com.example.assignment_four.model.Book;
import org.springframework.stereotype.Service;

@Service
public class AuthorConverter {
    public AuthorDTO toAuthorDTO (Author author) {
        AuthorDTO authorDTO=new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setBookTitle(author.getBook().stream().map(Book::getTitle).toList());
        return authorDTO;
    }
}
