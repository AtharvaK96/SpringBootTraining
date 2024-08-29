package com.spring.jpa.JPAExample.service;

import com.spring.jpa.JPAExample.model.Author;
import com.spring.jpa.JPAExample.model.Book;
import com.spring.jpa.JPAExample.model.Category;
import com.spring.jpa.JPAExample.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(int id){
        return bookRepository.findById(id);
    }

    public Optional<Book> getBookByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(int id, Book book){
        Author author = new Author(book.getAuthor().getId(), book.getAuthor().getName());
        Category category = new Category(book.getCategory().getId(), book.getCategory().getName());
        return bookRepository.save(new Book(id, book.getTitle(), book.getPrice(), author, category));
    }

    public void deleteBook(int id){
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksWithPriceAbove(int price){
        return bookRepository.findByPriceGreaterThan(price);
    }
}
