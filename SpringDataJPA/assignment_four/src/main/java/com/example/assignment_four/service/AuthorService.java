package com.example.assignment_four.service;

import ch.qos.logback.core.pattern.Converter;
import com.example.assignment_four.converter.AuthorConverter;
import com.example.assignment_four.dto.AuthorDTO;
import com.example.assignment_four.dto.BookDTO;
import com.example.assignment_four.model.Author;
import com.example.assignment_four.model.Book;
import com.example.assignment_four.repository.AuthorRepository;
import com.example.assignment_four.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorConverter authorConverter;

    public Page<AuthorDTO> displayAuthors(int pageNumber, int pageSize) {
        Sort sort=Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable= PageRequest.of(pageNumber, pageSize, sort);
        Page<Author> authorList =authorRepository.findAll(pageable);
        List<AuthorDTO> authorDTOList=authorList.stream().map(authorConverter:: toAuthorDTO).toList();
        return new PageImpl<>(authorDTOList);
    }
    public Optional<Author> findBooksByAuthor(String name) {
        return authorRepository.findByName(name);
    }
}
