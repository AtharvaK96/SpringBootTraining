package com.JpaAssignment.jpa_assignment.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private long bookId;
    private String bookTitle;
    private String authorName;

}
