package com.spring.jpa.JPAExample.mapper;

import com.spring.jpa.JPAExample.DTO.AuthorDTO;
import com.spring.jpa.JPAExample.model.Author;
import com.spring.jpa.JPAExample.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(source = "id", target = "authorId")
    @Mapping(source = "name", target = "authorName")
    @Mapping(source = "book", target = "bookTitle", qualifiedByName = "mapBookTitles")
    AuthorDTO toAuthorDTO(Author author);

    @Named("mapBookTitles")
    default List<String> mapBookTitles(List<Book> books) {
        return books.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }
}
