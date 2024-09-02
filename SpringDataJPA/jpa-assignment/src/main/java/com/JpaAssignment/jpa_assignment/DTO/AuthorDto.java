package com.JpaAssignment.jpa_assignment.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class AuthorDto {
    private long id;
    private String name;
    private List<String> bookTitle;
}
