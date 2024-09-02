package com.example.springDataJPA.services;

import com.example.springDataJPA.dtos.author.AuthorDto;
import com.example.springDataJPA.dtos.book.BookResponseDto;
import com.example.springDataJPA.exceptions.BookNotFoundException;
import com.example.springDataJPA.models.Author;
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

    public BookResponseDto getSingleBook(Integer id){
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book does not exists"));
//        return mapToBookDto(book);
        Author author = book.getAuthor();
        AuthorDto authorDto = new AuthorDto(author.getId(), author.getName(), author.getNationality());
        return new BookResponseDto(book.getId(),
               book.getTitle(),
               book.getPrice(),
               book.getLanguage(),
               authorDto,
               book.getCategory());
//        return book;

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
//private BookDto mapToBookDto(Book book){
//    BookDto bookDto = new BookDto();
//    bookDto.setId(book.getId());
//    bookDto.setTitle(book.getTitle());
//    bookDto.setPrice(book.getPrice());
//    bookDto.setLanguage(book.getLanguage());
//    bookDto.setAuthor(book.getAuthor());
//    bookDto.setCategory(book.getCategory());
//
//    return bookDto;
//}







}
