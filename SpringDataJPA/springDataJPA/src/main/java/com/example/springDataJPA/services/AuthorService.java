package com.example.springDataJPA.services;

import com.example.springDataJPA.dtos.author.AuthorDto;
import com.example.springDataJPA.mappers.AuthorMapper;
import com.example.springDataJPA.models.Author;
import com.example.springDataJPA.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthorService {
    AuthorRepository authorRepository;
    AuthorMapper authorMapper;


    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public AuthorDto getBooksByAuthorId(Integer id) {
        Author authorById = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
        return authorMapper.mapToAuthorDto(authorById);
    }

}
