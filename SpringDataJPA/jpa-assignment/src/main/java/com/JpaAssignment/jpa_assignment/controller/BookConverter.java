package com.JpaAssignment.jpa_assignment.controller;

import com.JpaAssignment.jpa_assignment.DTO.BookDto;
import org.springframework.stereotype.Service;


import com.JpaAssignment.jpa_assignment.DTO.BookDto;
import com.JpaAssignment.jpa_assignment.model.Book;
@Service
public class BookConverter {
    public BookDto convertToDto(Book book){
        BookDto bookDto=new BookDto();
        bookDto.setBookId(book.getId());
        bookDto.setBookTitle(book.getTitle());
        bookDto.setAuthorName(book.getAuthor().getName());
        return bookDto;
    }
}
