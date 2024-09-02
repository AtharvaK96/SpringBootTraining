package com.example.assignment_four.converter;

import com.example.assignment_four.dto.BookDTO;
import com.example.assignment_four.model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookConverter {
    public BookDTO toBookDTO (Book book) {
        BookDTO bookDTO =new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setAuthorName(book.getAuthor().getName());
        return bookDTO;
    }
}
