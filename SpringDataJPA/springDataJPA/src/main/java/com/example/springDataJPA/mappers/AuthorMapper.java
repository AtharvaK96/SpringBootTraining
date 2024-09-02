package com.example.springDataJPA.mappers;

import com.example.springDataJPA.dtos.author.AuthorDto;
import com.example.springDataJPA.dtos.book.BookDto;
import com.example.springDataJPA.models.Author;
import com.example.springDataJPA.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AuthorMapper {


    AuthorDto mapToAuthorDto(Author author);

    @Mapping(target = "author_name", source = "author.name")
    @Mapping(target = "category", source = "category.name")
    BookDto mapToBookDto(Book book);

}
