package java_training_jpa.JPATraining.controller;

import java_training_jpa.JPATraining.DTO.BookDto;
import java_training_jpa.JPATraining.model.Book;
import java_training_jpa.JPATraining.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.util.List;
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
                .map(bookConverter::convertEntityToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(bookDtoList);
    }
}
