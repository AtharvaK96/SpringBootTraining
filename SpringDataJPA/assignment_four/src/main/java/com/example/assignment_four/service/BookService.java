package com.example.assignment_four.service;

import com.example.assignment_four.controller.BookController;
import com.example.assignment_four.converter.BookConverter;
import com.example.assignment_four.dto.BookDTO;
import com.example.assignment_four.model.Author;
import com.example.assignment_four.model.Book;
import com.example.assignment_four.model.Category;
import com.example.assignment_four.repository.BookRepository;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.sun.source.tree.LambdaExpressionTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookConverter bookConverter;

    public Page<BookDTO> displayBooks(int pageNumber, int pageSize) {
        Sort sort=Sort.by(Sort.Direction.ASC, "author_name");
        Pageable pageable= PageRequest.of(pageNumber, pageSize, sort);
        Page<Book> bookList=bookRepository.findAll(pageable);
        List<BookDTO> bookDTOList=bookList.stream().map(bookConverter :: toBookDTO).toList();
        return new PageImpl<>(bookDTOList);
    }

    public Optional<Book> findBookById(int id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Optional<List<Book>> findBooksAbovePrice(double price) {
        return bookRepository.findByPriceGreaterThan(price);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(int id, Book book) {
        Author author = new Author(book.getAuthor().getId(),book.getAuthor().getName(), book.getAuthor().getBook());
        Category category = new Category(book.getCategory().getId(), book.getCategory().getName());
        return bookRepository.save(new Book(id, book.getTitle(), book.getPrice(), author, category));
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

}
