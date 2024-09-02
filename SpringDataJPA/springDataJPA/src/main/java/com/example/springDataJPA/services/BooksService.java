package com.example.springDataJPA.services;

import com.example.springDataJPA.dtos.book.BookDto;
import com.example.springDataJPA.exceptions.BookNotFoundException;
import com.example.springDataJPA.mappers.BookMapper;
import com.example.springDataJPA.models.Book;
import com.example.springDataJPA.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BooksService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookMapper bookMapper;


    public Page<BookDto> getAllBooks(int pageNumber, int pageSize, String sortBy) {

        Pageable pageRequest;
        if (Objects.isNull(sortBy) || sortBy.isEmpty()) {
            pageRequest = PageRequest.of(pageNumber, pageSize);
        } else {
            Sort customSort = Sort.by(Sort.Direction.ASC, sortBy);
            pageRequest = PageRequest.of(pageNumber, pageSize, customSort);
        }

        Page<Book> books = bookRepository.findAll(pageRequest);

        List<BookDto> bookDtoList = books.stream()
                .map(bookMapper::mapToBookDto)
                .collect(Collectors.toList());
        return new PageImpl<>(bookDtoList, pageRequest, books.getTotalElements());
    }


    public BookDto getSingleBook(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book does not exists"));
        return bookMapper.mapToBookDto(book);


    }

    public BookDto addBook(Book book) {
        Book savedBook = bookRepository.save(book);
        return bookMapper.mapToBookDto(savedBook);
    }

    public BookDto updateBook(Book book, Integer id) {
        Book bookFromDb = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("The Book does not exists"));
        bookFromDb.setTitle(book.getTitle());
        bookFromDb.setLanguage(book.getLanguage());
        bookFromDb.setCategory(book.getCategory());
        bookFromDb.setAuthor(book.getAuthor());
        System.out.println(bookFromDb);
        bookRepository.save(bookFromDb);
        return bookMapper.mapToBookDto(bookFromDb);

    }

    public String deleteBook(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("The Book does not exists"));
        bookRepository.delete(book);
        return "Book SuccessFully Deleted";
    }

    public BookDto findBookByName(String title) {

        Book byTitle = bookRepository.findByTitle(title);
        return bookMapper.mapToBookDto(byTitle);
    }

    public List<BookDto> findBooksPriceLessThan(Integer price) {

        return bookRepository.findByPriceLessThan(price).stream().map(bookMapper::mapToBookDto).collect(Collectors.toList());
    }


}
