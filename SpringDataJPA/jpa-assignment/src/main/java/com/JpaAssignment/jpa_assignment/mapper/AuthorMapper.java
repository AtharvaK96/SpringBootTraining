package com.JpaAssignment.jpa_assignment.mapper;

import com.JpaAssignment.jpa_assignment.DTO.AuthorDto;
import com.JpaAssignment.jpa_assignment.model.Author;
import com.JpaAssignment.jpa_assignment.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(source = "book", target = "bookTitle", qualifiedByName = "booksToTitles")
    AuthorDto toAuthorDTO(Author author);

    @Mapping(source = "bookTitle", target = "book", qualifiedByName = "titlesToBooks")
    Author toAuthor(AuthorDto authorDTO);

    @Named("booksToTitles")
    default List<String> booksToTitles(List<Book> books) {
        return books.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Named("titlesToBooks")
    default List<Book> titlesToBooks(List<String> titles) {
        return titles.stream()
                .map(title -> new Book())
                .collect(Collectors.toList());
    }
}
