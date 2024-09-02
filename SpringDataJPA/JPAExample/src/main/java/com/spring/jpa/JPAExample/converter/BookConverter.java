package com.spring.jpa.JPAExample.converter;

import com.spring.jpa.JPAExample.DTO.BookDTO;
import com.spring.jpa.JPAExample.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookConverter {

    public BookDTO toBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookID(book.getId());
        bookDTO.setBookTitle(book.getTitle());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setAuthorName(book.getAuthor().getName());
        return bookDTO;
    }
}
