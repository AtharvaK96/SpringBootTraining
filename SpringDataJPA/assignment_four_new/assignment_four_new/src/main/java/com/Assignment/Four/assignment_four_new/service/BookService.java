package com.Assignment.Four.assignment_four_new.service;

import com.Assignment.Four.assignment_four_new.model.Author;
import com.Assignment.Four.assignment_four_new.model.Book;
import com.Assignment.Four.assignment_four_new.model.Category;
import com.Assignment.Four.assignment_four_new.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public List<Book> displayBooks(){
        return bookRepository.findAll();
    }
    public Book addBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(@PathVariable Long id, @RequestBody Book book){
        Author author=new Author(book.getAuthor().getId(), book.getAuthor().getName());
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
