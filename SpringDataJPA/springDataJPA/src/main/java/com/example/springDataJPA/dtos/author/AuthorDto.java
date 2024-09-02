package com.example.springDataJPA.dtos.author;

import com.example.springDataJPA.dtos.book.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    private Integer id;
    private String name;
    private String nationality;
    private List<BookDto> books;
}
