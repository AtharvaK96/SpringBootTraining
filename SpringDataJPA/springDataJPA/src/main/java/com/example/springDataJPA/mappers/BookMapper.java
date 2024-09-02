package com.example.springDataJPA.mappers;

import com.example.springDataJPA.dtos.book.BookDto;
import com.example.springDataJPA.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface BookMapper {
    @Mapping(source = "author.name", target = "author_name")
    @Mapping(source = "category.name", target = "category")
    BookDto mapToBookDto(Book book);
}
