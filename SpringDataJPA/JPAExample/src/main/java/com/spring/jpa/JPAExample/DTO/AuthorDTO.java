package com.spring.jpa.JPAExample.DTO;

import lombok.Data;

import java.util.List;

@Data
public class AuthorDTO {
    private int authorId;
    private String authorName;
    private List<String> bookTitle;
}
