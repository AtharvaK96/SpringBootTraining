package com.example.springDataJPA.dtos.book;

import com.example.springDataJPA.dtos.author.AuthorDto;
import com.example.springDataJPA.models.Author;
import com.example.springDataJPA.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
    private Integer id;
    private String title;
    private Integer price;
    private String language;
    private AuthorDto author;
    private Category category;
}
