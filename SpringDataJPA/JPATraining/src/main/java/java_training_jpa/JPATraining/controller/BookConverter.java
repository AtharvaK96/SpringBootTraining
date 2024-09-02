package java_training_jpa.JPATraining.controller;

import java_training_jpa.JPATraining.DTO.BookDto;
import java_training_jpa.JPATraining.model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookConverter {


    public BookDto convertEntityToDto(Book book){
        BookDto bookDto=new BookDto();
        bookDto.setBookId(book.getId());
        bookDto.setBookTitle(book.getTitle());
        bookDto.setAuthorName(book.getAuthor().getName());
        return bookDto;
    }
}
