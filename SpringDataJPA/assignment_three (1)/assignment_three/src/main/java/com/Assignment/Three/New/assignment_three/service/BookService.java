package com.Assignment.Three.New.assignment_three.service;

import com.Assignment.Three.New.assignment_three.model.Book;
import com.Assignment.Three.New.assignment_three.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> fetchAllBooks(){
        return bookRepository.getAllBooks();
    }


    public String insertBook(Book book){
        return bookRepository.addBook(book);
    }

    public String updateBook(int id, Book book){
        return bookRepository.updateBook(book, id);
    }
    public String recordDeleted(int id){
        return bookRepository.deleteBook(id);
    }



}
