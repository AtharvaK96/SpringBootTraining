package com.example.springDataJPA.dtos.book;

import com.example.springDataJPA.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Integer id;
    private String title;
    private Integer price;
    private String language;
    private Category category;
}
