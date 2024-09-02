package com.spring.jpa.JPAExample.service;

import com.spring.jpa.JPAExample.DTO.BookDTO;
import com.spring.jpa.JPAExample.converter.BookConverter;
import com.spring.jpa.JPAExample.model.Author;
import com.spring.jpa.JPAExample.model.Book;
import com.spring.jpa.JPAExample.model.Category;
import com.spring.jpa.JPAExample.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookConverter bookConverter;

    public Page<BookDTO> getAllBooks(int pageNumber, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "author_name");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Book> bookList = bookRepository.findAll(pageable);

        List<BookDTO> bookDTOList = bookList.stream()
                .map(bookConverter::toBookDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(bookDTOList);
    }

    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(int id, Book book) {
        Author author = new Author(book.getAuthor().getId(), book.getAuthor().getName(), book.getAuthor().getBook());
        Category category = new Category(book.getCategory().getId(), book.getCategory().getName());
        return bookRepository.save(new Book(id, book.getTitle(), book.getPrice(), author, category));
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksWithPriceAbove(int price) {
        return bookRepository.findByPriceGreaterThan(price);
    }
}
