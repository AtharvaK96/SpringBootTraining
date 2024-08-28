package h2JDBCTraining.JdbcTempleteTraining.service;


import h2JDBCTraining.JdbcTempleteTraining.model.Book;
import h2JDBCTraining.JdbcTempleteTraining.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
