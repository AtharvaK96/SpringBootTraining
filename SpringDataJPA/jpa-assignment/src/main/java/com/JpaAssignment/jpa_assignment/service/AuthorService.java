package com.JpaAssignment.jpa_assignment.service;

import com.JpaAssignment.jpa_assignment.DTO.AuthorDto;
import com.JpaAssignment.jpa_assignment.DTO.BookDto;
import com.JpaAssignment.jpa_assignment.controller.AuthorConverter;
import com.JpaAssignment.jpa_assignment.model.Author;
import com.JpaAssignment.jpa_assignment.model.Book;
import com.JpaAssignment.jpa_assignment.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    AuthorConverter authorConverter;
//    public List<AuthorDto> getAllAuthors() {
//        return authorRepository.findAll()
//                .stream()
//                .map(authorConverter::convertsAuthorToDto)
//                .collect(Collectors.toList());
//    }
    public Page<AuthorDto> getAllAuthors(int pageNumber, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Author> authorList = authorRepository.findAll(pageable);

        List<AuthorDto> authorDtoList = authorList.stream()
                .map(authorConverter::convertsAuthorToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(authorDtoList);
    }
    public Optional <Author> findByAuthorName(String name){
        return authorRepository.findByName(name);
    }


}
