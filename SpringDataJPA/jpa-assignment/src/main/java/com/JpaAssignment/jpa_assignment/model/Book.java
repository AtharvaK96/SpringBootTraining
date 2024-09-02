package com.JpaAssignment.jpa_assignment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    Author author;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
