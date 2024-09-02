package com.example.assignment_four.mapper;

import com.example.assignment_four.dto.AuthorDTO;
import com.example.assignment_four.model.Author;
import com.example.assignment_four.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(source = "book", target = "bookTitle", qualifiedByName = "booksToTitles")
    AuthorDTO toAuthorDTO(Author author);

    @Named("booksToTitles")
    default List<String> booksToTitles(List<Book> books) {
        return books.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }
}
