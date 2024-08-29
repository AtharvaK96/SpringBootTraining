package com.example.springDataJPA.services;

import com.example.springDataJPA.exceptions.BookNotFoundException;
import com.example.springDataJPA.models.Book;
import com.example.springDataJPA.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BooksService {
    BookRepository bookRepository;

    @Autowired
    public BooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
       return bookRepository.findAll();
    }
     public Book addBook(Book book){
       return bookRepository.save(book);
     }

    public Book updateBook(Book book,Integer id){
        Book bookFromDb = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("The Book does not exists"));
        bookFromDb.setTitle(book.getTitle());
        bookFromDb.setLanguage(book.getLanguage());
        bookFromDb.setCategory(book.getCategory());
        bookFromDb.setAuthor(book.getAuthor());
//bookRepository.
//        bookRepository.save(bookFromDb);
        System.out.println(bookFromDb);
        bookRepository.save(bookFromDb);
        return bookFromDb;

    }

    public String deleteBook(Integer id){
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("The Book does not exists"));
        bookRepository.delete(book);
        return "Book SuccessFully Deleted";
    }
    public Book findBookByName(String title){
       return bookRepository.findByTitle(title);
    }

    public List<Book> findBooksPriceLessThan(Integer price){
        return bookRepository.findByPriceLessThan(price);
    }



//    --------------------------------------------------------
//private Book mapToBook




}
