package com.spring.jpa.JPAExample.service;

import com.spring.jpa.JPAExample.DTO.AuthorDTO;
import com.spring.jpa.JPAExample.DTO.BookDTO;
import com.spring.jpa.JPAExample.converter.AuthorConverter;
import com.spring.jpa.JPAExample.mapper.AuthorMapper;
import com.spring.jpa.JPAExample.model.Author;
import com.spring.jpa.JPAExample.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

//    @Autowired
//    AuthorConverter authorConverter;

    @Autowired
    AuthorMapper authorMapper;

//    public <Optional> AuthorDTO getAuthor(int id) {
//        Author author = authorRepository.findById(id).orElse(null);
//        return authorConverter.toAuthorDTO(author);
//    }

//    public Page<AuthorDTO> getAllAuthors(int pageNumber, int pageSize) {
//        Sort sort = Sort.by(Sort.Direction.ASC, "name");
//        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
//
//        Page<Author> authorList = authorRepository.findAll(pageable);
//
//        List<AuthorDTO> authorDTOList = authorList.stream()
//                .map(authorConverter::toAuthorDTO)
//                .collect(Collectors.toList());
//        return new PageImpl<>(authorDTOList);
//    }

    public <Optional> AuthorDTO getAuthor(int id) {
        Author author = authorRepository.findById(id).orElse(null);
        return authorMapper.toAuthorDTO(author);
    }

    public Page<AuthorDTO> getAllAuthors(int pageNumber, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Author> authorList = authorRepository.findAll(pageable);

        List<AuthorDTO> authorDTOList = authorList.stream()
                .map(authorMapper::toAuthorDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(authorDTOList);
    }
}
