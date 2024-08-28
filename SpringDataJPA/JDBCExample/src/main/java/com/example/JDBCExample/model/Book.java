package com.example.JDBCExample.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Book {
    private int id;
    private String title;
    private int price;
}
