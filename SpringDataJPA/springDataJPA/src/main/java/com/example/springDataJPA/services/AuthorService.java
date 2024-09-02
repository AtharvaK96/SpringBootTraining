package com.example.springDataJPA.services;

import com.example.springDataJPA.dtos.author.AuthorBooksRespnseDto;
import com.example.springDataJPA.dtos.book.BookDto;
import com.example.springDataJPA.models.Author;
import com.example.springDataJPA.repository.AuthorRepository;
import com.example.springDataJPA.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    AuthorRepository authorRepository;
    BookRepository bookRepository;



    @Autowired
    public AuthorService(AuthorRepository authorRepository,BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    public AuthorBooksRespnseDto getBooksByAuthorId(Integer id){
        Author authorById = authorRepository.findById(id).orElseThrow(()->new RuntimeException("Author not found"));
        List<BookDto> books = authorById.getBooks().stream()
                .map((book)->new BookDto(book.getId(),book.getTitle(),book.getPrice(),book.getLanguage(),book.getCategory()))
                .collect(Collectors.toList());

       return new AuthorBooksRespnseDto(authorById.getId(), authorById.getName(), authorById.getNationality(), books );

    }
    public Author getBooksByAuthorTestingId(Integer id){
        Author authorById = authorRepository.findById(id).orElseThrow(()->new RuntimeException("Author not found"));
//        List<BookDto> books = authorById.getBooks().stream()
//                .map((book)->new BookDto(book.getId(),book.getTitle(),book.getPrice(),book.getLanguage(),book.getCategory()))
//                .collect(Collectors.toList());
        return authorById;
//        return new AuthorBooksRespnseDto(authorById.getId(), authorById.getName(), authorById.getNationality(), books );

    }


}
