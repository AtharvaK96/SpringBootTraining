package com.example.springDataJPA.dtos.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    private Integer id;
    private String name;
    private String nationality;
}
