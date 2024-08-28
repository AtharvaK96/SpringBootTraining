package com.Assignment.Three.New.assignment_three.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Book {
    private int id;
    private String title;
    private int price;
}
