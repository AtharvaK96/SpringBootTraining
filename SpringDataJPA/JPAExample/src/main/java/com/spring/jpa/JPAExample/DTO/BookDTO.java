package com.spring.jpa.JPAExample.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class BookDTO {
    private int bookID;
    private String bookTitle;
    private int price;
    private String authorName;
}
