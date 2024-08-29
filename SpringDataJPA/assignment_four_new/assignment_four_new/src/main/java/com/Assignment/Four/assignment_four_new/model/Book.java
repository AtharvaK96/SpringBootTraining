package com.Assignment.Four.assignment_four_new.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private double price;
    @ManyToOne
    @JoinColumn(name="author_id")
    Author author;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
