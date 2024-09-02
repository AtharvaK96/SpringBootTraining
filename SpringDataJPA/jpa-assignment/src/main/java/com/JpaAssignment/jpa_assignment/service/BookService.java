package com.JpaAssignment.jpa_assignment.service;

import com.JpaAssignment.jpa_assignment.DTO.BookDto;
import com.JpaAssignment.jpa_assignment.controller.BookConverter;
import com.JpaAssignment.jpa_assignment.model.Author;
import com.JpaAssignment.jpa_assignment.model.Book;
import com.JpaAssignment.jpa_assignment.model.Category;
import com.JpaAssignment.jpa_assignment.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookConverter bookConverter;

    public Page<BookDto> getAllBooks(int pageNumber, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "title");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Book> bookList = bookRepository.findAll(pageable);

        List<BookDto> bookDtoList = bookList.stream()
                .map(bookConverter::convertToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(bookDtoList);
    }
    public Book addBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(@PathVariable Long id, @RequestBody Book book){
        Author author=new Author(book.getAuthor().getId(), book.getAuthor().getName(), book.getAuthor().getBook());
        Category category=new Category(book.getCategory().getId(), book.getCategory().getName());

        return bookRepository.save(new Book(id, book.getTitle(), book.getPrice(), author, category));

    }
    public void delete(@PathVariable long id){
        bookRepository.deleteById(id);
    }
    public Optional<Book> findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findBooksLessThan500() {
        return bookRepository.findByPriceLessThan(500);
    }
    public List<Book> findBooksGreaterThan500() {
        return bookRepository.findByPriceGreaterThan(500);
    }

}
