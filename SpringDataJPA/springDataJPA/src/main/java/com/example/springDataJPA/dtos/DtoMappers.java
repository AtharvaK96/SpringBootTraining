package com.example.springDataJPA.dtos;

import com.example.springDataJPA.dtos.book.BookDto;
import com.example.springDataJPA.dtos.author.AuthorBooksRespnseDto;
import com.example.springDataJPA.models.Author;
import com.example.springDataJPA.models.Book;

public class DtoMappers {
    public static BookDto mapToBookDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setPrice(book.getPrice());
        bookDto.setLanguage(book.getLanguage());
//        bookDto.setAuthor(book.getAuthor());
        bookDto.setCategory(book.getCategory());

        return bookDto;
    }
    public static AuthorBooksRespnseDto mapToAuthorDto(Author author) {
        AuthorBooksRespnseDto authorDto = new AuthorBooksRespnseDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        authorDto.setNationality(author.getNationality());
//        authorDto.setBooks(author.getBooks());
        return authorDto;
    }
}
